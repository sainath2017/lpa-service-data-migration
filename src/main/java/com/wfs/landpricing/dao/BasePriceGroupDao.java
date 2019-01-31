package com.wfs.landpricing.dao;

import com.wfs.landpricing.model.BasePriceGroup;
import com.wfs.landpricing.model.enums.YesNo;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BasePriceGroupDao extends JpaRepository<BasePriceGroup, Long> {

  Page<BasePriceGroup> findAll(Pageable pageable);

  List<BasePriceGroup> findAllByActive(YesNo yesNo);
}
