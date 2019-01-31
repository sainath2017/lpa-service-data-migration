package com.wfs.landpricing.model;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Entity
@Table(name = "product_mapping")
public class ProductMapping {

  @EmbeddedId
  private ProductMappingId id;

  private Long mappingId;

  private String companyId;

}
