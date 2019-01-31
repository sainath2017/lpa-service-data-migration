package com.wfs.landpricing.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import com.wfs.landpricing.dto.TerminalMappingDto;
import com.wfs.landpricing.model.TerminalMapping;

/**
 * @author vsrivastava
 * @since 10/26/18
 **/
@Mapper(componentModel = "spring")
public interface TerminalMappingMapper {

  @Mappings({
      @Mapping(source = "terminalName", target = "id.terminalName"),
      @Mapping(source = "terminalId", target = "id.terminalId")
  })
  TerminalMapping toModel(TerminalMappingDto dto);
}
