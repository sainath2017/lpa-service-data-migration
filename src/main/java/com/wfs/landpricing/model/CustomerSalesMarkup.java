package com.wfs.landpricing.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.wfs.landpricing.model.enums.CustomerType;
import com.wfs.landpricing.model.enums.PnrType;
import com.wfs.landpricing.model.enums.YesNo;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@EqualsAndHashCode(of = {"id"})
@Entity
@Table(name = "customer_sales_markup")
public class CustomerSalesMarkup {

  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "customer_sales_markup_id_sequence")
  @SequenceGenerator(name = "customer_sales_markup_id_sequence", sequenceName = "customer_sales_markup_id_sequence", allocationSize = 1)
  private Long id;

  @ManyToOne(fetch = FetchType.EAGER)
  @JoinColumn(name = "customer_site_id")
  private CustomerSite customerSite;

  @ManyToOne(fetch = FetchType.EAGER)
  @JoinColumn(name = "propsect_site_id")
  private ProspectCustomerSite prospectCustomer;

  @OneToOne(fetch = FetchType.EAGER)
  @JoinColumn(name = "base_price_id")
  private BasePrice basePrice;

  private LocalDateTime effectiveDate;

  private String customerName;
  private String location;
  private String salespersonEmail;

  @Enumerated(value = EnumType.STRING)
  private CustomerType customerType;

  @Enumerated(value = EnumType.STRING)
  private YesNo active = YesNo.Y;
  private Long productId;
  private String productName;

  private Long terminalId;
  private String terminalTcn;
  private String terminalName;

  private Long haulerId;
  private Long haulerSiteId;
  private String haulerSiteName;
  private String haulerName;
  private String haulerFobFlag;
  private String supplierName;
  private Long supplierId;
  private String supplierAlias;
  
  private Long brandId;
  private String brandName;

  @Enumerated(value = EnumType.STRING)
  private YesNo includeTerminal;
  @Enumerated(value = EnumType.STRING)
  private PnrType pnrType;

  private BigDecimal markup = BigDecimal.ZERO;
  private BigDecimal preSalesTaxTotal = BigDecimal.ZERO;
  private BigDecimal proposedPrice = BigDecimal.ZERO;;

  private BigDecimal salesTax = BigDecimal.ZERO;;
  private BigDecimal salesTaxPercentRate = BigDecimal.ZERO;;
  private BigDecimal stateTax = BigDecimal.ZERO;;
  private BigDecimal federalTax = BigDecimal.ZERO;;
  private BigDecimal otherTax = BigDecimal.ZERO;;
  private BigDecimal freight = BigDecimal.ZERO;;
  private BigDecimal tax = BigDecimal.ZERO;;

  private BigDecimal basePriceAmount;
  private String basePriceGroupName;
  private Long basePriceGroupId;
  private String createdBy;
  private String lastUpdatedBy;
  private LocalDateTime creationDate;
  private LocalDateTime lastUpdated;


}
