package com.wfs.landpricing.model;

import com.wfs.landpricing.model.enums.YesNo;
import java.time.LocalDateTime;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Getter;
import lombok.Setter;

/**
 * @author vsrivastava
 * @since 10/26/18
 **/
@Getter
@Setter
@Entity
@Table(name = "supplier")
public class Supplier {
  @Id
  private Long supplierId;
  @Enumerated(EnumType.STRING)
  private YesNo active;
  private String createdBy;
  private LocalDateTime creationDate;
  private LocalDateTime lastUpdated;
  private String lastUpdatedBy;
  private String supplierName;

}
