package com.wfs.landpricing.dto;

import com.opencsv.bean.CsvBindByName;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class ProductTerminalDto {
	
 @CsvBindByName(column = "productname")
  private String productName;
 
 @CsvBindByName(column = "terminaltcn")
  private String terminalTcn;
 
 @CsvBindByName(column = "terminalname")
  private String terminalName;

}
