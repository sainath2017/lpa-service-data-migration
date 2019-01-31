package com.wfs.landpricing.dto;

import lombok.Getter;
import lombok.Setter;

/**
 * @author vsrivastava
 * @since 10/26/18
 **/
@Getter
@Setter
public class SupplierMappingDto {
  private Long mappingId;
  private String brandName;
  private Long companyId;
  private Long supplierId;
  private String supplierAlias;

}
