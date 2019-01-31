package com.wfs.landpricing.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.wfs.landpricing.model.TerminalMapping;
import com.wfs.landpricing.model.TerminalMappingId;

@Repository
public interface TerminalMappingDao extends JpaRepository<TerminalMapping, TerminalMappingId> {

  TerminalMapping findTopById_TerminalName(String terminalName);

}
