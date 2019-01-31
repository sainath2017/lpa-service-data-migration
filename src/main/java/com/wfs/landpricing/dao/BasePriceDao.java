package com.wfs.landpricing.dao;

import com.wfs.landpricing.model.BasePrice;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BasePriceDao extends JpaRepository<BasePrice, String> {


}
