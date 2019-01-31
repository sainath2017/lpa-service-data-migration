package com.wfs.landpricing.service;

import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import com.wfs.landpricing.dao.BasePriceGroupDao;
import com.wfs.landpricing.dto.BasePriceDto;
import com.wfs.landpricing.dto.BasePriceGroupDto;
import com.wfs.landpricing.mapper.BasePriceGroupMapper;
import com.wfs.landpricing.mapper.BasePriceMapper;
import com.wfs.landpricing.model.BasePriceGroup;
import com.wfs.landpricing.model.BasePriceRaw;
import com.wfs.landpricing.model.enums.YesNo;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

/**
 * @author vsrivastava
 * @since 11/19/18
 **/
@Service
public class BasePriceMigrationService {

  @Autowired
  private BasePriceGroupDao bpGrpDao;



  @Autowired
  private AsyncService asyncService;
  @Autowired
  private BasePriceGroupMapper bpGrpMapper;
  @Autowired
  private BasePriceMapper bpMapper;

  @Autowired
  private DaoService daoService;





  private static Logger log = LoggerFactory.getLogger(BasePriceMigrationService.class);

  @Async
  public void migrateBpGrp(String fileName) {
    try{
      Reader reader = Files.newBufferedReader(Paths.get(fileName));
      CsvToBean<BasePriceGroupDto> csvToBean = new CsvToBeanBuilder(reader).withType(BasePriceGroupDto.class).withOrderedResults(false)
          .withIgnoreLeadingWhiteSpace(true).build();
      Iterator<BasePriceGroupDto> csvBeanIterator = csvToBean.iterator();
      int lineCount = 0;
      System.out.println("lineCount " + lineCount);
      while (csvBeanIterator.hasNext()) {
        ++lineCount;
        BasePriceGroupDto dto = csvBeanIterator.next();
        BasePriceGroup entity = bpGrpMapper.toModel(dto);
        bpGrpDao.save(entity);

      }
    } catch (Exception e) {
      log.error("Error Migrating Historical Tables", e);
    }
  }


  @Async
  public void migrateBasePriceRaw(String fileName){
    try{

      List<BasePriceRaw> entities = new ArrayList<>();
      List<BasePriceGroup> groups = bpGrpDao.findAll();

      Map<String, BasePriceGroup> map = groups.stream().collect(Collectors.toMap(BasePriceGroup::getName, bpgrp->bpgrp));

      Reader reader = Files.newBufferedReader(Paths.get(fileName));
      CsvToBean<BasePriceDto> csvToBean = new CsvToBeanBuilder(reader).withType(BasePriceDto.class).withOrderedResults(false)
          .withIgnoreLeadingWhiteSpace(true).build();
      Iterator<BasePriceDto> csvBeanIterator = csvToBean.iterator();
      int lineCount = 0;
      System.out.println("lineCount " + lineCount);
      while (csvBeanIterator.hasNext()) {
        ++lineCount;

        BasePriceDto dto = csvBeanIterator.next();
        BasePriceRaw entity = convertToModel(dto, map);
        if(entity != null){
          entities.add(entity);
        }else{
          log.info("no base price group found for name {}", dto.getBasePriceGroupName());
        }
        if(lineCount%1000 == 0){
          List<BasePriceRaw> bps = copyList(entities);
          daoService.saveRawList(bps);
          entities.clear();
          lineCount =0;
        }
      }

      if(!entities.isEmpty()){
        log.info("saving left over base prices");
        daoService.saveRawList(entities);
      }
    } catch (Exception e) {
      log.error("Error Migrating Historical Tables", e);
    }

  }

  public BasePriceRaw convertToModel(BasePriceDto dto, Map<String, BasePriceGroup> map){
    BasePriceRaw entity = bpMapper.toRawModel(dto);
    BasePriceGroup group = map.get(dto.getBasePriceGroupName());
    if(group != null && entity.getEffectiveDate() !=null ){
      entity.setBasePriceGroupName(dto.getBasePriceGroupName());
    }else{
      entity = null;
    }
    return entity;
  }

  private <T> List<T>  copyList(List<T> data){
    List<T> list = new ArrayList<>();
    for (T hs: data) {
      list.add(hs);
    }
    return list;
  }

  @Async
  public void migrateBasePrice(){
    try{
      List<BasePriceGroup> groups = bpGrpDao.findAllByActive(YesNo.Y);
      for (BasePriceGroup gp:groups) {
        log.info(" migrating for base price group = {}", gp.getName());
        asyncService.saveBP2(gp);
      }

    } catch (Exception e) {
      log.error("Error Migrating Historical Tables", e);
    }

  }
}
