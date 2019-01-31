package com.wfs.landpricing.dao;

import com.wfs.landpricing.model.BasePriceRaw;
import java.util.List;
import java.util.Optional;
import javax.persistence.Tuple;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * @author vsrivastava
 * @since 10/17/18
 **/
@Repository
public interface BasePriceRawDao extends JpaRepository<BasePriceRaw, Long> {

  Page<BasePriceRaw> findByBasePriceGroupNameOrderByEffectiveDateAsc(String group, Pageable pageable);

  @Query(value = "select distinct product_alias, terminal_tcn  from base_price_raw  where base_price_group_name = :name", nativeQuery = true)
  List<Tuple> getAllUniqueProductAndTcnForGroupNative(@Param("name") String name);

  Optional<BasePriceRaw> findTopByBasePriceGroupNameAndProductAliasAndTerminalTcnOrderByEffectiveDateDesc(String basePriceGroupName, String productAlias, String terminalTcn);

}
