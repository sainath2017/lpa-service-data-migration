package com.wfs.landpricing.dao;

import com.wfs.landpricing.model.CustomerSite;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerSiteDao extends JpaRepository<CustomerSite, Long> {


}
