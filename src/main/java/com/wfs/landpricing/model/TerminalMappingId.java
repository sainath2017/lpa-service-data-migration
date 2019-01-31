/**
 *
 */
package com.wfs.landpricing.model;

import java.io.Serializable;

import javax.persistence.Embeddable;

import lombok.Getter;
import lombok.Setter;

/**
 * @author vsrivastava
 */
@Getter
@Setter
@Embeddable
public class TerminalMappingId implements Serializable {

  private static final long serialVersionUID = 1L;

  private String terminalName;
  private Long terminalId;

}
