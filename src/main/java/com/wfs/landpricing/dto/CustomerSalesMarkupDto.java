package com.wfs.landpricing.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

import com.opencsv.bean.CsvBindByName;
import com.opencsv.bean.CsvCustomBindByName;
import com.wfs.landpricing.model.enums.CustomerType;
import com.wfs.landpricing.model.enums.PnrType;
import com.wfs.landpricing.model.enums.YesNo;
import com.wfs.landpricing.util.CSvStringToCustomerType;
import com.wfs.landpricing.util.CSvStringToPnrType;
import com.wfs.landpricing.util.CSvStringToUUIDConverter;
import com.wfs.landpricing.util.CsvStringToLocalDateTimeConverter;
import com.wfs.landpricing.util.CsvStringToYesNoConverter;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class CustomerSalesMarkupDto {


  @CsvBindByName(column = "base_price_group")
  private String basePriceGroupRawId;

  @CsvCustomBindByName(column = "base_price_effective_date", converter = CsvStringToLocalDateTimeConverter.class)
  private LocalDateTime effectiveDate;

  @CsvBindByName(column = "bse_price_name")
  private String basePriceGroupName;

  @CsvBindByName(column = "company_code")
  private Long companyCode;

  @CsvBindByName(column = "company_id")
  private Long companyId;

  @CsvBindByName(column = "customer_id")
  private Long customerId;


  @CsvBindByName(column = "customer_name")
  private String customerName;
  @CsvBindByName(column = "base_price_value")
  private BigDecimal basePriceAmount;


  @CsvCustomBindByName(column = "customer_type", converter = CSvStringToCustomerType.class)
  private CustomerType customerType;

  @CsvBindByName(column = "federal_tax")
  private BigDecimal federalTax;

  @CsvBindByName(column = "freight")
  private BigDecimal freight;

  @CsvBindByName(column = "hauler_id")
  private Long haulerId;
  @CsvBindByName(column = "hauler_name")
  private String haulerName;
  @CsvBindByName(column = "hauler_site_id")
  private Long haulerSiteId;

  @CsvCustomBindByName(column = "include_terminal",converter = CsvStringToYesNoConverter.class)
  private YesNo includeTerminal;

  @CsvCustomBindByName(column = "is_active",converter = CsvStringToYesNoConverter.class)
  private YesNo active;

  @CsvBindByName(column = "mark_up")
  private BigDecimal markup;

  @CsvBindByName(column = "other_tax")
  private BigDecimal otherTax;

  @CsvCustomBindByName(column = "pnr_type",converter = CSvStringToPnrType.class)
  private PnrType pnrType;

  @CsvBindByName(column = "pre_sales_tax_total")
  private BigDecimal preSalesTaxTotal;

  @CsvCustomBindByName(column = "price_last_update",converter = CsvStringToLocalDateTimeConverter.class)
  private LocalDateTime lastUpdated;

  @CsvBindByName(column = "product")
  private String productName;

  @CsvBindByName(column = "product_id")
  private Long productId;

  @CsvBindByName(column = "proposed_price")
  private BigDecimal proposedPrice;

  @CsvCustomBindByName(column = "prospect_id", converter = CSvStringToUUIDConverter.class)
  private UUID prospectId;

  @CsvBindByName(column = "sales_tax")
  private BigDecimal salesTax;

  @CsvBindByName(column = "sales_tax_percent_rate")
  private BigDecimal salesTaxPercentRate;

  @CsvBindByName(column = "salespersonemail")
  private String salespersonEmail;

  @CsvBindByName(column = "salespersonname")
  private String salesPersonName;

  @CsvBindByName(column = "site_address")
  private String location;

  @CsvBindByName(column = "site_id")
  private Long customerSiteId;

  @CsvBindByName(column = "state_tax")
  private BigDecimal stateTax;

  @CsvBindByName(column = "supplier")
  private String supplierName;
  
  private String supplierAlias;

  @CsvBindByName(column = "tax")
  private BigDecimal tax;



  @CsvBindByName(column = "tcn")
  private String terminalTcn;
  @CsvBindByName(column = "terminal_id")
  private Long terminalId;
  @CsvBindByName(column = "terminal_name")
  private String terminalName;



  private String haulerSiteName;


}
