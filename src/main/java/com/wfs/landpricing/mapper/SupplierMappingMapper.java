package com.wfs.landpricing.mapper;

import com.wfs.landpricing.dto.SupplierMappingDto;
import com.wfs.landpricing.model.SupplierMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

/**
 * @author vsrivastava
 * @since 10/26/18
 **/
@Mapper(componentModel = "spring")
public interface SupplierMappingMapper {

  @Mappings({
      @Mapping(source = "supplierId", target = "id.supplierId"),
      @Mapping(source = "supplierAlias", target = "id.supplierAlias")
  })
  SupplierMapping toModel(SupplierMappingDto dto);
}
