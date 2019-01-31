package com.wfs.landpricing.model;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @author vsrivastava
 */
@Getter
@Setter
@ToString
@Table(name = "customer_hauler")
@Entity
public class CustomerHauler {

  @EmbeddedId
  private CustomerHaulerId id;

  private Integer haulerRank;

  private String haulerName;

  private String haulerSiteName;

  private Long companyId;

  private String fobFlag;

}
