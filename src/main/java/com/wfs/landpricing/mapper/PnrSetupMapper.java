package com.wfs.landpricing.mapper;

import com.wfs.landpricing.dto.PnrSetupCustomerDto;
import com.wfs.landpricing.dto.PnrSetupProspectDto;
import com.wfs.landpricing.model.PnrSetup;
import org.mapstruct.Mapper;

/**
 * @author vsrivastava
 * @since 10/19/18
 **/
@Mapper(componentModel = "spring")
public interface PnrSetupMapper {

  PnrSetup toModel(PnrSetupProspectDto dto);

  PnrSetup toModel(PnrSetupCustomerDto dto);

}
