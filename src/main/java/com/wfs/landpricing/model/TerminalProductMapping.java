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
@Table(name = "terminal_product_mapping")
public class TerminalProductMapping {

  @EmbeddedId
  private TerminalProductMappingId id;
  private String terminalTcn;

}
