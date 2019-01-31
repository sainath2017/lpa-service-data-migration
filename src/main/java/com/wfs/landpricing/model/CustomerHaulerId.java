/**
 *
 */
package com.wfs.landpricing.model;

import com.wfs.landpricing.model.enums.YesNo;
import java.io.Serializable;
import java.time.LocalDateTime;
import javax.persistence.Embeddable;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import lombok.Getter;
import lombok.Setter;

/**
 * @author vsrivastava
 */
@Getter
@Setter
@Embeddable
public class CustomerHaulerId implements Serializable {

  private static final long serialVersionUID = 1L;
  private Long customerSiteId;
  private Long haulerId;
  private Long haulerSiteId;
  private LocalDateTime startDate;
  private LocalDateTime endDate;
  @Enumerated(EnumType.STRING)
  private YesNo active;
}
