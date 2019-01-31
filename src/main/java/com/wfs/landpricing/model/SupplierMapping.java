package com.wfs.landpricing.model;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * Created by sbhat on 25/08/2017.
 */

@Getter
@Setter
@ToString
@Entity
@Table(name = "supplier_mapping")
public class SupplierMapping {

  @EmbeddedId
  private SupplierMappingId id;
  private Long mappingId;
  private String brandName;
  private Long companyId;

}
