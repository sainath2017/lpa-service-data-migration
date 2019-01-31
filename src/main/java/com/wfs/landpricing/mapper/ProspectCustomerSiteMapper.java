package com.wfs.landpricing.mapper;

import com.wfs.landpricing.dto.ProspectCustomerSiteDto;
import com.wfs.landpricing.model.ProspectCustomerSite;
import org.mapstruct.Mapper;

/**
 * @author vsrivastava
 * @since 11/1/18
 **/
@Mapper(componentModel = "spring")
public interface ProspectCustomerSiteMapper {

  ProspectCustomerSite toModel(ProspectCustomerSiteDto dto);

}
