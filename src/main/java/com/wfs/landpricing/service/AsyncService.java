package com.wfs.landpricing.service;

import com.wfs.landpricing.dao.BasePriceRawDao;
import com.wfs.landpricing.mapper.BasePriceMapper;
import com.wfs.landpricing.model.BasePrice;
import com.wfs.landpricing.model.BasePriceGroup;
import com.wfs.landpricing.model.BasePriceRaw;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import javax.persistence.Tuple;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

/**
 * @author vsrivastava
 * @since 11/19/18
 **/
@Service
public class AsyncService {

  private static final Logger log = LoggerFactory.getLogger(AsyncService.class);

  @Autowired
  private BasePriceRawDao bpRawDao;


  @Autowired
  private BasePriceMapper bpMapper;

  @Autowired
  private DaoService daoService;

  @Async
  public void saveBP(BasePriceGroup gp){
    int page = 0;
    int limit = 1000;
    Pageable pageable = null;
    Page<BasePriceRaw> bprs = null;
    do{
      pageable = PageRequest.of(page, limit);
      bprs =   bpRawDao.findByBasePriceGroupNameOrderByEffectiveDateAsc(gp.getName(), pageable);
      List<BasePrice> bps = new ArrayList<>();
      for (BasePriceRaw raw:bprs) {
        BasePrice bp = bpMapper.toModelFromRaw(raw);
        bp.setBasePriceGroup(gp);
        populateBasePriceKey(bp);
        bps.add(bp);
      }
      daoService.saveBPList(bps);
      long left = bprs.getTotalElements() - page*limit;
      log.info("BP Group Name  {},  total count {},  left over {}, page {}", gp.getName(), bprs.getTotalElements()
      , left, page);

      page++;
    }while (bprs.getTotalElements()> page*limit);

  }
  private void populateBasePriceKey(BasePrice bp) {
    String key = bp.getTerminalTcn() + "|" + bp.getProductAlias()+ "|" + bp.getBasePriceGroup().getId();
    bp.setId(key);
  }

  @Async
  public void saveBP2(BasePriceGroup gp){
      log.info("START BP Group Name  {}", gp.getName());

      List<Tuple> tuples = bpRawDao.getAllUniqueProductAndTcnForGroupNative(gp.getName());


      List<BasePrice> bps = new ArrayList<>();
      for (Tuple dto:tuples) {

        Optional<BasePriceRaw>
            optionalRaw = bpRawDao.findTopByBasePriceGroupNameAndProductAliasAndTerminalTcnOrderByEffectiveDateDesc(
                gp.getName(),dto.get("product_alias", String.class),dto.get("terminal_tcn", String.class));
        if(optionalRaw.isPresent()){
          BasePrice bp = bpMapper.toModelFromRaw(optionalRaw.get());
          bp.setBasePriceGroup(gp);
          populateBasePriceKey(bp);
          bps.add(bp);
        }else{
          log.info("no rows found for group {}, product {}, tcn {}", gp.getName(), dto.get("product_alias", String.class), dto.get("terminal_tcn", String.class));
        }
      }
      daoService.saveBPList(bps);
  }
}
