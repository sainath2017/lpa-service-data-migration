package com.wfs.landpricing.model;

import com.wfs.landpricing.model.enums.YesNo;
import java.time.LocalDate;
import java.util.UUID;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * Prospects entity
 */
@Getter
@Setter
@ToString
@Entity
@Table(name = "prospect_customer_site")
public class ProspectCustomerSite {

  @Id
  private UUID id;

  private String prospectName;

  private String prospectSiteName;

  private String prospectAddress;

  @OneToOne(fetch = FetchType.EAGER)
  @JoinColumn(name = "customer_site_id")
  private CustomerSite customerSite;

  private String customerSiteName;

  private String customerName;

  private LocalDate startDate;

  private LocalDate endDate;

  private LocalDate dateAdded;

  private String userName;

  @Enumerated(EnumType.STRING)
  private YesNo active;

}
