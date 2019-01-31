package com.wfs.landpricing.dao;

import com.wfs.landpricing.model.TerminalProductMapping;
import com.wfs.landpricing.model.TerminalProductMappingId;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TerminalProductMappingDao extends JpaRepository<TerminalProductMapping, TerminalProductMappingId> {

  Page<TerminalProductMapping> findAll(Pageable pageable);

  List<TerminalProductMapping> findById_ProductAlias(String productAlias);

}
