package com.wfs.landpricing.mapper;

import com.wfs.landpricing.dto.CustomerSalesMarkupDto;
import com.wfs.landpricing.model.CustomerSalesMarkup;
import com.wfs.landpricing.model.CustomerSite;
import com.wfs.landpricing.model.ProspectCustomerSite;
import org.mapstruct.Mapper;

/**
 * @author vsrivastava
 * @since 10/19/18
 **/
@Mapper(componentModel = "spring")
public abstract class CustomerSalesMarkupMapper {

  public abstract CustomerSalesMarkup toShortModel(CustomerSalesMarkupDto dto);

  public CustomerSalesMarkup toModel(CustomerSalesMarkupDto dto) {
    CustomerSalesMarkup model = toShortModel(dto);
    switch (dto.getCustomerType()) {
      case CUSTOMER:
        CustomerSite customerSite = new CustomerSite();
        customerSite.setCustomerSiteId(dto.getCustomerSiteId());
        model.setCustomerSite(customerSite);
        break;
      case PROSPECT:
        ProspectCustomerSite prospectCustomerSite = new ProspectCustomerSite();
        prospectCustomerSite.setId(dto.getProspectId());
        model.setProspectCustomer(prospectCustomerSite);
    }
    return model;
  }



}
