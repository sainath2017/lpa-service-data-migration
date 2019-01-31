package com.wfs.landpricing.service;

import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import com.wfs.landpricing.dao.BasePriceDao;
import com.wfs.landpricing.dao.BasePriceGroupDao;
import com.wfs.landpricing.dao.CustomerHaulerDao;
import com.wfs.landpricing.dao.CustomerSalesMarkupDao;
import com.wfs.landpricing.dao.CustomerSiteDao;
import com.wfs.landpricing.dao.ProspectCustomerSiteDao;
import com.wfs.landpricing.dao.SupplierBrandDao;
import com.wfs.landpricing.dao.SupplierMappingDao;
import com.wfs.landpricing.dto.CustomerSalesMarkupDto;
import com.wfs.landpricing.mapper.CustomerSalesMarkupMapper;
import com.wfs.landpricing.model.BasePrice;
import com.wfs.landpricing.model.BasePriceGroup;
import com.wfs.landpricing.model.CustomerHauler;
import com.wfs.landpricing.model.CustomerSalesMarkup;
import com.wfs.landpricing.model.CustomerSite;
import com.wfs.landpricing.model.ProspectCustomerSite;
import com.wfs.landpricing.model.SupplierBrand;
import com.wfs.landpricing.model.SupplierMapping;
import com.wfs.landpricing.model.enums.YesNo;

/**
 * @author vsrivastava
 * @since 11/27/18
 **/
@Service
public class CustomerSalesMarkupMigrationService {


  private static Logger log = LoggerFactory.getLogger(CustomerSalesMarkupMigrationService.class);


  @Autowired
  private CustomerSalesMarkupMapper mapper;
  @Autowired
  private CustomerSalesMarkupDao customerSalesMarkupDao;

  @Autowired
  private ProspectCustomerSiteDao prospectCustomerSiteDao;

  @Autowired
  private CustomerSiteDao customerSiteDao;

  @Autowired
  private BasePriceGroupDao bpGrpDao;

  @Autowired
  private BasePriceDao bpDao;


  @Autowired
  private SupplierMappingDao supplierMappingDao;
  @Autowired
  private CustomerHaulerDao customerHaulerDao;

  @Autowired
  private SupplierBrandDao supplierBrandDao;



  public String migrateCustomerSalesMarkup(String fileName) {
    StringBuilder errors = new StringBuilder();
    try{
      Reader reader = Files.newBufferedReader(Paths.get(fileName));
      CsvToBean<CustomerSalesMarkupDto> csvToBean = new CsvToBeanBuilder(reader).withType(CustomerSalesMarkupDto.class).withOrderedResults(false)
          .withIgnoreLeadingWhiteSpace(true).build();
      Iterator<CustomerSalesMarkupDto> csvBeanIterator = csvToBean.iterator();

      List<BasePriceGroup> groups = bpGrpDao.findAllByActive(YesNo.Y);
      Map<String, List<BasePriceGroup>> groupMap = groups.stream().collect(Collectors.groupingBy(BasePriceGroup::getName));

      while (csvBeanIterator.hasNext()) {
        CustomerSalesMarkupDto dto = csvBeanIterator.next();
        CustomerSalesMarkup entity = mapper.toShortModel(dto);
        entity.setCreationDate(LocalDateTime.now());
        entity.setCreatedBy(dto.getSalespersonEmail());


        if(dto.getProspectId() != null ){
          Optional<ProspectCustomerSite> prospectCustomerSiteOptional = prospectCustomerSiteDao.findById(dto.getProspectId());
          ProspectCustomerSite prospectCustomerSite =prospectCustomerSiteOptional.orElse(null);
          entity.setProspectCustomer(prospectCustomerSite);
        }else if(dto.getCustomerSiteId() != null && dto.getCustomerSiteId() != 0l){
          Optional<CustomerSite> customerSiteOptional = customerSiteDao.findById(dto.getCustomerSiteId());
          CustomerSite customerSite = customerSiteOptional.orElse(null);
          entity.setCustomerSite(customerSite);
        }
        //Terminal terminal = terminalDao.findById(dto.getTerminalId()).get();
        populateHaulerFobFlag(dto, entity);
        populateSupplierId(dto, entity);

        BasePriceGroup gp = groupMap.get(dto.getBasePriceGroupName()).get(0);
        Optional<BasePrice> optionalBasePrice= getBasePrice(dto, gp, dto.getTerminalTcn() );
        //entity.setTerminalTcn(terminal.getTcn());
        if(optionalBasePrice.isPresent()){
          entity.setBasePrice(optionalBasePrice.get());
          entity.setBasePriceGroupId(optionalBasePrice.get().getBasePriceGroup().getId());
          populateBrandInfo(optionalBasePrice.get().getSupplierAlias(), entity);
        }else{
          errors.append(dto.toString()+"\n");
          errors.append("-------------------------------------------------------------------\n");

          log.info("base price not found for tcn {} , base price group id {},  product = {}", dto.getTerminalTcn(), gp.getId(), dto.getProductName());
        }
          entity.setEffectiveDate(dto.getEffectiveDate());
          entity.setBasePriceAmount(dto.getBasePriceAmount());
          entity.setBasePriceGroupName(dto.getBasePriceGroupName());
          customerSalesMarkupDao.save(entity);
      }
    } catch (Exception e) {
      log.error("Error Migrating Historical Tables", e);
    }

    return errors.toString();

  }

  private void populateSupplierId(CustomerSalesMarkupDto dto, CustomerSalesMarkup entity) {
    Optional<SupplierMapping> optionalSupplierMapping = supplierMappingDao.findFirstById_SupplierAlias(dto.getSupplierName());
    if(optionalSupplierMapping.isPresent()){
      Long supplierId = optionalSupplierMapping.get().getId().getSupplierId();
      entity.setSupplierId(supplierId);
    }
  }

  private void populateHaulerFobFlag(CustomerSalesMarkupDto dto, CustomerSalesMarkup entity){
    if(dto.getCustomerSiteId() !=null){
      Optional<CustomerHauler> optionalCustomerHauler = customerHaulerDao.getCustomerHauler(dto.getCustomerSiteId(), dto.getHaulerId(), dto.getHaulerSiteId());
      if(optionalCustomerHauler.isPresent()){
        entity.setHaulerFobFlag(optionalCustomerHauler.get().getFobFlag());
        entity.setHaulerSiteName(optionalCustomerHauler.get().getHaulerSiteName());
      }
    }
  }
  private Optional<BasePrice> getBasePrice(CustomerSalesMarkupDto dto, BasePriceGroup group, String tcn ){


    return bpDao.findById(generateBasePriceKey(tcn, dto.getProductName(), group.getId()));
  }

  private String generateBasePriceKey(String tcn, String productAlias, Long basePriceGroupId) {
    return tcn + "|" + productAlias + "|" + basePriceGroupId;
  }

private void populateBrandInfo(String supplierAlias, CustomerSalesMarkup entity) {
	  
	  Optional<SupplierBrand> optionalSuplierBrand =supplierBrandDao.findFirstById_SupplierAlias(supplierAlias);
	  if(optionalSuplierBrand.isPresent()) {
		  entity.setBrandId(optionalSuplierBrand.get().getId().getBrandId());
		  entity.setBrandName(optionalSuplierBrand.get().getBrandName());
	  }
}
}
