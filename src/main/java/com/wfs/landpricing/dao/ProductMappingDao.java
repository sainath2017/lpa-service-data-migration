package com.wfs.landpricing.dao;

import com.wfs.landpricing.model.ProductMapping;
import com.wfs.landpricing.model.ProductMappingId;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductMappingDao extends JpaRepository<ProductMapping, ProductMappingId> {

  Optional<ProductMapping> findTopById_ProductAliasOrderById(String productAlias);



}
