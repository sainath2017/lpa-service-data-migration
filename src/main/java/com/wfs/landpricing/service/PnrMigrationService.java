package com.wfs.landpricing.service;

import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Iterator;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import com.wfs.landpricing.dao.CustomerSiteDao;
import com.wfs.landpricing.dao.PnrSetupDao;
import com.wfs.landpricing.dao.ProspectCustomerSiteDao;
import com.wfs.landpricing.dto.PnrSetupCustomerDto;
import com.wfs.landpricing.dto.PnrSetupProspectDto;
import com.wfs.landpricing.mapper.PnrSetupMapper;
import com.wfs.landpricing.model.CustomerSite;
import com.wfs.landpricing.model.PnrSetup;
import com.wfs.landpricing.model.ProspectCustomerSite;
import com.wfs.landpricing.model.enums.PnrType;

/**
 * @author vsrivastava
 * @since 11/20/18
 **/
@Service
public class PnrMigrationService {


  private static Logger log = LoggerFactory.getLogger(PnrMigrationService.class);


  @Autowired
  private PnrSetupMapper pnrSetupMapper;

  @Autowired
  private ProspectCustomerSiteDao prospectCustomerSiteDao;

  @Autowired
  private CustomerSiteDao customerSiteDao;

  @Autowired
  private PnrSetupDao pnrSetupDao;

  @Async
  public void migrateProspectPNR(String fileName) {
    try{
      Reader reader = Files.newBufferedReader(Paths.get(fileName));
      CsvToBean<PnrSetupProspectDto> csvToBean = new CsvToBeanBuilder(reader).withType(PnrSetupProspectDto.class).withOrderedResults(false)
          .withIgnoreLeadingWhiteSpace(true).build();
      Iterator<PnrSetupProspectDto> csvBeanIterator = csvToBean.iterator();

      int lineCount = 0;

      while (csvBeanIterator.hasNext()) {
        ++lineCount;
        PnrSetupProspectDto dto = csvBeanIterator.next();
        PnrSetup entity = pnrSetupMapper.toModel(dto);
        if(dto.getProspectSiteId() != null ){
          Optional<ProspectCustomerSite> prospectCustomerSiteOptional = prospectCustomerSiteDao.findById(dto.getProspectSiteId());
          ProspectCustomerSite prospectCustomerSite =prospectCustomerSiteOptional.orElse(null);
          entity.setProspectCustomerSite(prospectCustomerSite);
        }
        fixPnrType(entity);
        pnrSetupDao.save(entity);
        log.info("line count {}", lineCount);
      }
    } catch (Exception e) {
      log.error("Error Migrating pnr setup for prospect ", e);
    }
  }

  private void fixPnrType(PnrSetup entity){
    if(entity.getPnrType() == null ){
      entity.setPnrType(PnrType.DELIVERED_PRICE_ONLY);
    }
  }

  @Async
  public void migrateCustomerPNR(String fileName) {
    try{
      Reader reader = Files.newBufferedReader(Paths.get(fileName));
      CsvToBean<PnrSetupCustomerDto> csvToBean = new CsvToBeanBuilder(reader).withType(PnrSetupCustomerDto.class).withOrderedResults(false)
          .withIgnoreLeadingWhiteSpace(true).build();
      Iterator<PnrSetupCustomerDto> csvBeanIterator = csvToBean.iterator();

      int lineCount = 0;

      while (csvBeanIterator.hasNext()) {
        ++lineCount;
        PnrSetupCustomerDto dto = csvBeanIterator.next();
        PnrSetup entity = pnrSetupMapper.toModel(dto);
                if(dto.getCustomerSiteId() != null && dto.getCustomerSiteId() != 0l){
                  Optional<CustomerSite> customerSiteOptional = customerSiteDao.findById(dto.getCustomerSiteId());
                  CustomerSite customerSite =customerSiteOptional.orElse(null);
                  entity.setCustomerSite(customerSite);
                  //log.info(" customer site id = {}", dto.getCustomerSiteId());
                }else{
                  log.info("missing customer site id = {}", dto.getCustomerSiteId());
                }
        fixPnrType(entity);
        pnrSetupDao.save(entity);
        log.info("line count {}", lineCount);
      }
    } catch (Exception e) {
      log.error("Error Migrating pnr setup for prospect ", e);
    }
  }
}
