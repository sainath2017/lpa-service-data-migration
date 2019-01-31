package com.wfs.landpricing.service;

import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import com.wfs.landpricing.dao.CustomerSiteDao;
import com.wfs.landpricing.dao.PnrSetupDao;
import com.wfs.landpricing.dao.ProspectCustomerSiteDao;
import com.wfs.landpricing.dto.PnrSetupProspectDto;
import com.wfs.landpricing.dto.ProspectCustomerSiteDto;
import com.wfs.landpricing.mapper.PnrSetupMapper;
import com.wfs.landpricing.mapper.ProspectCustomerSiteMapper;
import com.wfs.landpricing.model.CustomerSite;
import com.wfs.landpricing.model.PnrSetup;
import com.wfs.landpricing.model.ProspectCustomerSite;
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

/**
 * @author vsrivastava
 * @since 11/20/18
 **/
@Service
public class ProspectMigrationService {

  private static Logger log = LoggerFactory.getLogger(ProspectMigrationService.class);

  @Autowired
  private ProspectCustomerSiteMapper prospectCustomerSiteMapper;

  @Autowired
  private ProspectCustomerSiteDao prospectCustomerSiteDao;

  @Autowired
  private CustomerSiteDao customerSiteDao;


  @Async
  public void migrateProspect(String fileName){
    try{
      Reader reader = Files.newBufferedReader(Paths.get(fileName));
      CsvToBean<ProspectCustomerSiteDto> csvToBean = new CsvToBeanBuilder(reader).withType(ProspectCustomerSiteDto.class).withOrderedResults(false)
          .withIgnoreLeadingWhiteSpace(true).build();
      Iterator<ProspectCustomerSiteDto> csvBeanIterator = csvToBean.iterator();

      int lineCount = 0;

      while (csvBeanIterator.hasNext()) {
        ++lineCount;
        ProspectCustomerSiteDto dto = csvBeanIterator.next();
        ProspectCustomerSite entity = prospectCustomerSiteMapper.toModel(dto);
        if(dto.getCustomerSiteId() != null && dto.getCustomerSiteId() != 0l){
          Optional<CustomerSite> customerSiteOptional = customerSiteDao.findById(dto.getCustomerSiteId());
          CustomerSite customerSite =customerSiteOptional.orElse(null);
          entity.setCustomerSite(customerSite);
        }
        prospectCustomerSiteDao.save(entity);
        log.info("line count {}", lineCount);
      }
    } catch (Exception e) {
      log.error("Error Migrating prospect ", e);
    }

  }


}
