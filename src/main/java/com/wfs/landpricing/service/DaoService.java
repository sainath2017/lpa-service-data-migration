package com.wfs.landpricing.service;

import com.wfs.landpricing.dao.BasePriceDao;
import com.wfs.landpricing.dao.BasePriceRawDao;
import com.wfs.landpricing.model.BasePrice;
import com.wfs.landpricing.model.BasePriceRaw;
import java.util.List;
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
public class DaoService {

  private static final Logger log = LoggerFactory.getLogger(DaoService.class);

  @Autowired
  private BasePriceRawDao bpRawDao;

  @Autowired
  private BasePriceDao bpDao;

  @Async
  public void saveRawList(List<BasePriceRaw> entities){
    log.info("save single entity base price raw. {}", Thread.currentThread().getName());
    bpRawDao.saveAll(entities);
  }

  @Async
  public void saveBPList(List<BasePrice> entities){
    log.info("save single entity base price. {}", Thread.currentThread().getName());
    bpDao.saveAll(entities);
  }
}
