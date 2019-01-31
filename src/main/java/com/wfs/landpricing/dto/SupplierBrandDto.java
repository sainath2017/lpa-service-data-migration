package com.wfs.landpricing.dto;

import com.wfs.landpricing.model.enums.YesNo;
import java.time.LocalDateTime;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @author vsrivastava
 * @since 10/26/18
 **/
@Getter
@Setter
@ToString
public class SupplierBrandDto {
  private Long brandId;
  private String supplierAlias;
  private YesNo active;
  private String brandName;
  private String createdBy;
  private LocalDateTime creationDate;
  private LocalDateTime lastUpdated;
  private String lastUpdatedBy;

}
