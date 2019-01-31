package com.wfs.landpricing.model;

import java.io.Serializable;
import javax.persistence.Embeddable;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @author vsrivastava
 * @since 10/26/18
 **/
@Getter
@Setter
@EqualsAndHashCode
@Embeddable
@ToString
public class SupplierBrandId implements Serializable {
  private static final long serialVersionUID = 1L;

  private Long brandId;
  private String supplierAlias;

}
