package com.wfs.landpricing.model;

/**
 * @author vsrivastava
 * @since 10/26/18
 **/

import com.wfs.landpricing.model.enums.YesNo;
import java.time.LocalDateTime;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@Entity
@Table(name = "supplier_brand")
@ToString
public class SupplierBrand {

  @EmbeddedId
  private SupplierBrandId id;
  @Enumerated(EnumType.STRING)
  private YesNo active;
  private String brandName;
  private String createdBy;
  private LocalDateTime creationDate;
  private LocalDateTime lastUpdated;
  private String lastUpdatedBy;

}
