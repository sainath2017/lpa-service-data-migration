package com.wfs.landpricing.mapper;

import com.wfs.landpricing.dto.BasePriceDto;
import com.wfs.landpricing.model.BasePrice;
import com.wfs.landpricing.model.BasePriceRaw;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

/**
 * @author vsrivastava
 * @since 10/17/18
 **/
@Mapper(componentModel = "spring")
public interface BasePriceMapper {

  BasePrice toModel(BasePriceDto dto);
  BasePriceRaw toRawModel(BasePriceDto dto);

  BasePrice toModelFromRaw(BasePriceRaw raw);

}
