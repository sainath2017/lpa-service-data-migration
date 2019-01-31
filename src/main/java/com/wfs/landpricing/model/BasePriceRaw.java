package com.wfs.landpricing.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @author vsrivastava
 * @since 10/17/18
 **/
@Getter
@Setter
@ToString
@Entity
@Table(name = "base_price_raw")
public class BasePriceRaw {


  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "base_price_raw_id_sequence")
  @SequenceGenerator(name = "base_price_raw_id_sequence", sequenceName = "base_price_raw_id_sequence", allocationSize = 1)
  private Long id;


  private String basePriceGroupName;
  private String productAlias;
  private LocalDateTime effectiveDate;
  private String terminalTcn;
  private String supplierAlias;
  private BigDecimal amount;
  private String terminalName;

}
