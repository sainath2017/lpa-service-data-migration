package com.wfs.landpricing.dao;

import com.wfs.landpricing.model.CustomerHauler;
import com.wfs.landpricing.model.CustomerHaulerId;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerHaulerDao extends JpaRepository<CustomerHauler, CustomerHaulerId> {

  List<CustomerHauler> findById_CustomerSiteIdAndId_StartDateLessThanEqualAndId_EndDateGreaterThanEqual(Long customerSiteId, LocalDateTime stDate,
      LocalDateTime enDate);

  @Query("SELECT c from CustomerHauler  c WHERE c.id.customerSiteId = :customerSiteId AND c.id.haulerId= :haulerId AND c.id.haulerSiteId = :haulerSiteId AND active = 'Y' "
      + " AND c.id.startDate <= CURRENT_TIMESTAMP AND c.id.endDate >= CURRENT_TIMESTAMP ")
  Optional<CustomerHauler> getCustomerHauler(@Param("customerSiteId") Long customerSiteId, @Param("haulerId") Long haulerId, @Param("haulerSiteId") Long HaulerSiteId);
}
