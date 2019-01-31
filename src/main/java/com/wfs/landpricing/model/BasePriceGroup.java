package com.wfs.landpricing.model;

import com.wfs.landpricing.model.enums.YesNo;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@EqualsAndHashCode(of = {"id"})
@Table(name = "base_price_group")
@Entity
public class BasePriceGroup {

  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "base_price_group_id_sequence")
  @SequenceGenerator(name = "base_price_group_id_sequence", sequenceName = "base_price_group_id_sequence", allocationSize = 1)
  private Long id;

  private String name;

  private String filenameConvention;

  @Enumerated(EnumType.STRING)
  private YesNo active;

}
