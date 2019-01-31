package com.wfs.landpricing.model;

import java.util.List;
import java.util.Set;

import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.wfs.landpricing.model.converter.EnumSetConverter;
import com.wfs.landpricing.model.converter.StringListConverter;
import com.wfs.landpricing.model.enums.CustomerType;
import com.wfs.landpricing.model.enums.EmailAttachmentType;
import com.wfs.landpricing.model.enums.PnrMethod;
import com.wfs.landpricing.model.enums.PnrType;
import com.wfs.landpricing.model.enums.PricingBreakout;
import com.wfs.landpricing.model.enums.ScheduleFrequency;
import com.wfs.landpricing.model.enums.TaxType;
import com.wfs.landpricing.model.enums.YesNo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Entity
@Table(name = "pnr_setup")
public class PnrSetup {

  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "pnr_setup_id_sequence")
  @SequenceGenerator(name = "pnr_setup_id_sequence", sequenceName = "pnr_setup_id_sequence", allocationSize = 1)
  private Long id;

  @OneToOne(fetch = FetchType.EAGER)
  @JoinColumn(name = "customer_site_id")
  private CustomerSite customerSite;

  @OneToOne(fetch = FetchType.EAGER)
  @JoinColumn(name = "prospect_site_id")
  private ProspectCustomerSite prospectCustomerSite;

  private String customerName;
  private String location;
  private String companyName;
  private String loginEmail;

  @Enumerated(EnumType.STRING)
  private CustomerType customerType;
  private String dtnNumber;

  @Convert(converter = StringListConverter.class)
  private List<String> emailAddresses;

  @Enumerated(EnumType.STRING)
  private EmailAttachmentType emailAttachmentType;
  private boolean hasAttachment;
  @Enumerated(EnumType.STRING)
  private PnrType pnrType;
  private boolean includeSubTotals;
  private boolean grouped;
  private String messageAttention;
  @Enumerated(EnumType.STRING)
  private ScheduleFrequency pnrScheduleFrequency;
  private String pnrScheduleTiming;
  @Enumerated(EnumType.STRING)
  private YesNo pnrScheduleType;

  @Convert(converter = StringListConverter.class)
  private List<String> pnrScheduleWeekday;

  @Convert(converter = EnumSetConverter.class)
  private Set<PnrMethod> pnrMethod;
  @Enumerated(EnumType.STRING)
  private PricingBreakout pricingBreakout;
  private boolean productDescription;
  private String specialNotes;
  @Enumerated(EnumType.STRING)
  private TaxType taxType;
}
