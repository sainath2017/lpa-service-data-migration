package com.wfs.landpricing.mapper;

import com.wfs.landpricing.dto.BasePriceGroupDto;
import com.wfs.landpricing.model.BasePriceGroup;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface BasePriceGroupMapper {


  BasePriceGroup toModel(BasePriceGroupDto basePriceGroupDto);


}
