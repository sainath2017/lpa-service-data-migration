package com.wfs.landpricing.dto;

import java.util.List;
import java.util.UUID;

import com.opencsv.bean.CsvBindByName;
import com.opencsv.bean.CsvCustomBindByName;
import com.wfs.landpricing.model.enums.CustomerType;
import com.wfs.landpricing.model.enums.EmailAttachmentType;
import com.wfs.landpricing.model.enums.PnrMethod;
import com.wfs.landpricing.model.enums.PnrType;
import com.wfs.landpricing.model.enums.PricingBreakout;
import com.wfs.landpricing.model.enums.ScheduleFrequency;
import com.wfs.landpricing.model.enums.TaxType;
import com.wfs.landpricing.model.enums.YesNo;
import com.wfs.landpricing.util.CSvStringToCustomerType;
import com.wfs.landpricing.util.CSvStringToEmailAttachmentType;
import com.wfs.landpricing.util.CSvStringToPnrType;
import com.wfs.landpricing.util.CSvStringToTaxSummaryType;
import com.wfs.landpricing.util.CSvStringToUUIDConverter;
import com.wfs.landpricing.util.CsvStringJsonToSetConverter;
import com.wfs.landpricing.util.CsvStringJsonToSetPnrMethodConverter;
import com.wfs.landpricing.util.CsvStringToPricingBreakout;
import com.wfs.landpricing.util.CsvStringToScheduleFrequency;
import com.wfs.landpricing.util.CsvStringToYesNoConverter;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class PnrSetupProspectDto {




  @CsvBindByName(column = "customer_name")
  private String customerName;


  @CsvBindByName(column = "company_name")
  private String companyName;

  @CsvCustomBindByName(column ="prospect_id", converter = CSvStringToUUIDConverter.class)
  private UUID prospectSiteId;

  @CsvBindByName(column ="prospect_name")
  private String prospectName;

  @CsvBindByName(column ="prospect_name")
  private String prospectSiteName;



  @CsvBindByName(column ="location")
  private String location;

  private String prospectAddress;

  @CsvCustomBindByName(column ="customer_type", converter = CSvStringToCustomerType.class)
  private CustomerType customerType;

  @CsvBindByName(column ="dtn_number")
  private String dtnNumber;

  @CsvCustomBindByName(column ="email_addresses", converter = CsvStringJsonToSetConverter.class)
  private List<String> emailAddresses;

  @CsvCustomBindByName(column ="email_attachment_type", converter = CSvStringToEmailAttachmentType.class)
  private EmailAttachmentType emailAttachmentType;

  
  @CsvBindByName(column ="has_attachment")
  private boolean hasAttachment;

  @CsvCustomBindByName(column ="include_item_type", converter = CSvStringToPnrType.class)
  private PnrType pnrType;
  @CsvBindByName(column ="include_sub_totals")
  private boolean includeSubTotals;

  @CsvBindByName(column ="is_grouped")
  private boolean grouped;

  @CsvBindByName(column ="message_attention")
  private String messageAttention;

  @CsvCustomBindByName(column ="pnr_schedule_frequency", converter = CsvStringToScheduleFrequency.class)
  private ScheduleFrequency pnrScheduleFrequency;

  @CsvBindByName(column ="pnr_schedule_timing")
  private String pnrScheduleTiming;

  @CsvCustomBindByName(column ="pnr_schedule_type", converter = CsvStringToYesNoConverter.class)
  private YesNo pnrScheduleType;

  @CsvCustomBindByName(column ="pnr_schedule_weekday", converter = CsvStringJsonToSetConverter.class)
  private List<String> pnrScheduleWeekday;

  @CsvCustomBindByName(column ="pnr_type", converter = CsvStringJsonToSetPnrMethodConverter.class)
  private List<PnrMethod> pnrMethod;

  @CsvCustomBindByName(column ="pricing_type", converter = CsvStringToPricingBreakout.class)
  private PricingBreakout pricingBreakout;

  @CsvBindByName(column ="product_description")
  private boolean productDescription;

  @CsvBindByName(column ="special_notes")
  private String specialNotes;

  @CsvCustomBindByName(column ="tax_type", converter = CSvStringToTaxSummaryType.class)
  private TaxType taxType;
  @CsvBindByName(column ="user_id")
  private String userName;

}
