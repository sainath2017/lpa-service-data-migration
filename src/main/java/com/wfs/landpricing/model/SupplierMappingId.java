package com.wfs.landpricing.model;

import java.io.Serializable;
import javax.persistence.Embeddable;
import lombok.Getter;
import lombok.Setter;

/**
 * @author vsrivastava
 * @since 10/17/18
 **/
@Getter
@Setter
@Embeddable
public class SupplierMappingId implements Serializable {
  private static final long serialVersionUID = 1L;

  private Long supplierId;
  private String supplierAlias;
}
