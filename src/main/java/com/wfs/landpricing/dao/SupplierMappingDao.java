package com.wfs.landpricing.dao;

/**
 * @author vsrivastava
 * @since 12/3/18
 **/

import com.wfs.landpricing.model.SupplierMapping;
import com.wfs.landpricing.model.SupplierMappingId;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SupplierMappingDao extends JpaRepository<SupplierMapping, SupplierMappingId> {

  Optional<SupplierMapping> findFirstById_SupplierAlias(String supplierAlias);

}
