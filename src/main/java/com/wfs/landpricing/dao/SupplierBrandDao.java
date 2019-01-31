package com.wfs.landpricing.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.wfs.landpricing.model.SupplierBrand;
import com.wfs.landpricing.model.SupplierBrandId;

/**
 * @author vsrivastava
 * @since 10/26/18
 **/
@Repository
public interface SupplierBrandDao extends JpaRepository<SupplierBrand, SupplierBrandId> {

	Optional<SupplierBrand> findFirstById_SupplierAlias(String supplierAlias);
}
