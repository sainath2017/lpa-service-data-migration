package com.wfs.landpricing.dto;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author vsrivastava
 * @since 11/20/18
 **/
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class ProductAndTcnDto {

  private String productAlias;
  private String terminalTcn;
}
