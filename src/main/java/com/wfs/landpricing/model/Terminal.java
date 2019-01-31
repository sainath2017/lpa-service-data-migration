package com.wfs.landpricing.model;

import com.wfs.landpricing.model.enums.YesNo;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @author vsrivastava
 * @since 10/18/18
 **/
@Getter
@Setter
@ToString
@Entity
@Table(name = "terminal")
public class Terminal {

  @Id
  private Long terminalId;

  private String address;

  private String city;

  @Enumerated(EnumType.STRING)
  private YesNo active;

  private String country;

  private String state;

  private String tcn;

  private String name;

  private String zip;

  private String description;
}
