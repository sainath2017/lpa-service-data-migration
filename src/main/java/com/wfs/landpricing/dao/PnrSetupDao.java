package com.wfs.landpricing.dao;

import com.wfs.landpricing.model.PnrSetup;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PnrSetupDao extends JpaRepository<PnrSetup, Long> {

}
