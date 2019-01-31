package com.wfs.landpricing.dto;

import com.opencsv.bean.CsvBindByName;
import com.opencsv.bean.CsvCustomBindByName;
import com.wfs.landpricing.model.enums.YesNo;
import com.wfs.landpricing.util.CSvStringToUUIDConverter;
import com.wfs.landpricing.util.CsvStringToYesNoConverter;
import java.util.UUID;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * Created by sbhat on 11/14/2017.
 */

@Getter
@Setter
@ToString
public class BasePriceGroupDto {

  @CsvBindByName(column = "basepricegroup")
  private String name;

  @CsvBindByName(column = "filenameconv")
  private String filenameConvention;

  @CsvCustomBindByName(column = "isactive", converter = CsvStringToYesNoConverter.class)
  private YesNo active;

  @CsvBindByName(column = "id")
  private String extId;



}
