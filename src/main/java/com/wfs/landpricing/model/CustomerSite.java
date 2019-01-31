package com.wfs.landpricing.model;

import com.wfs.landpricing.model.enums.YesNo;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
@EqualsAndHashCode(of = {"customerSiteId"})
@Entity
@Table(name = "customer_site")
public class CustomerSite {

  @Id
  private Long customerSiteId;
  private String customerSiteName;
  private Long customerId;
  private String customerName;
  private Long customerNumber;
  @Enumerated(EnumType.STRING)
  private YesNo activeCustomer;
  @Enumerated(EnumType.STRING)
  private YesNo activeCustomerSite;
  private String address;
  private Long billToSiteId;
  private String city;
  private Long companyCode;
  private Long companyId;
  private String currencyCode;
  private Long destinationHrlocationId;
  private String postalCode;
  private String salesperson;
  private String salespersonEmail;
  private String stateProvince;
  private String uom;

  public String getLocation() {
    return  getCustomerSiteName()+"(" + getAddress()+", "+getCity()+", "+getPostalCode()+", "+getStateProvince() + ")";
  }

}
