package com.wfs.landpricing.dto;

import com.opencsv.bean.CsvBindByName;
import com.opencsv.bean.CsvCustomBindByName;
import com.wfs.landpricing.model.enums.YesNo;
import com.wfs.landpricing.util.CSvStringToUUIDConverter;
import com.wfs.landpricing.util.CsvStringToLocalDate;
import com.wfs.landpricing.util.CsvStringToYesNoConverter;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.UUID;

@Getter
@Setter
public class ProspectCustomerSiteDto {

  @CsvCustomBindByName(column = "prospect_id" , converter = CSvStringToUUIDConverter.class)
  private UUID id;

  @CsvBindByName(column = "customer_id" )
  private Long customerId;
  @CsvBindByName(column = "customer_name")
  private String customerName;
  @CsvBindByName(column = "customer_site_id")
  private Long customerSiteId;
  @CsvBindByName(column = "customer_site_name")
  private String customerSiteName;
  @CsvCustomBindByName(column = "date_added",converter = CsvStringToLocalDate.class)
  private LocalDate dateAdded;
  @CsvCustomBindByName(column = "end_date",converter = CsvStringToLocalDate.class)
  private LocalDate endDate;
  @CsvCustomBindByName(column = "is_active", converter = CsvStringToYesNoConverter.class)
  private YesNo active;

  @CsvBindByName(column = "prospect_address")
  private String prospectAddress;
  @CsvBindByName(column = "prospect_name")
  private String prospectName;
  @CsvBindByName(column = "prospect_site_name")
  private String prospectSiteName;

  @CsvCustomBindByName(column = "start_date",converter = CsvStringToLocalDate.class)
  private LocalDate startDate;

  @CsvBindByName(column = "user_id")
  private String userName;

}
