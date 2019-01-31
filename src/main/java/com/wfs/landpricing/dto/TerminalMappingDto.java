package com.wfs.landpricing.dto;

import lombok.Getter;
import lombok.Setter;

/**
 * @author vsrivastava
 * @since 10/26/18
 **/
@Getter
@Setter
public class TerminalMappingDto {
  private Long mappingId;
  private String terminalName;
  private Long terminalId;
}
