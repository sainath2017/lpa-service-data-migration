package com.wfs.landpricing.dao;

import com.wfs.landpricing.model.ProspectCustomerSite;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProspectCustomerSiteDao extends JpaRepository<ProspectCustomerSite, UUID> {

  Optional<ProspectCustomerSite> findTopByProspectNameIgnoreCaseAndProspectSiteNameIgnoreCase(String prospectName, String prospectSiteName);

  List<ProspectCustomerSite> findAllByProspectNameContaining(String prospectName, Pageable pageable);

}
