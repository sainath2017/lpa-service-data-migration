package com.wfs.landpricing.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import com.opencsv.bean.CsvBindByName;
import com.opencsv.bean.CsvCustomBindByName;
import com.wfs.landpricing.util.CsvStringToLocalDateTimeConverter;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class BasePriceDto {

  @CsvBindByName(column = "product")
  private String productAlias;
  @CsvCustomBindByName(column = "effectivedate", converter = CsvStringToLocalDateTimeConverter.class)
  private LocalDateTime effectiveDate;
  @CsvBindByName(column = "terminaltcn")
  private String terminalTcn;
  @CsvBindByName(column = "supplier")
  private String supplierAlias;
  @CsvBindByName(column = "amount")
  private BigDecimal amount;
  @CsvBindByName(column = "terminalname")
  private String terminalName;
  @CsvBindByName(column = "price_type")
  private String basePriceGroupName;

}
