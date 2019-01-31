package com.wfs.landpricing.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * id is created by concatenating 3 columns termincalTcn, productAlias, basePriceGroupId
 */

@Getter
@Setter
@ToString
@Entity
@Table(name = "base_price")
public class BasePrice {

  @Id
  private String id;
  private String productAlias;
  private LocalDateTime effectiveDate;
  private String terminalTcn;
  private String supplierAlias;
  private BigDecimal amount;
  private String terminalName;

  @ManyToOne(fetch = FetchType.EAGER)
  @JoinColumn(name = "base_price_group_id", referencedColumnName = "id")
  private BasePriceGroup basePriceGroup;

}
