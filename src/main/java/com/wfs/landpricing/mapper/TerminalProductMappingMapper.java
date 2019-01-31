package com.wfs.landpricing.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import com.wfs.landpricing.dto.ProductTerminalDto;
import com.wfs.landpricing.model.TerminalProductMapping;

/**
 * @author vsrivastava
 * @since 10/19/18
 **/
@Mapper(componentModel = "spring")
public interface TerminalProductMappingMapper {

  @Mappings({
      @Mapping(source = "productName", target = "id.productAlias"),
      @Mapping(source = "terminalName", target = "id.terminalName"),
      @Mapping(source = "terminalTcn", target = "terminalTcn")
  })
  TerminalProductMapping toModel(ProductTerminalDto dto);
}
