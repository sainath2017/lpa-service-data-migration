package com.wfs.landpricing.mapper;

import com.wfs.landpricing.dto.ProductMappingDto;
import com.wfs.landpricing.model.ProductMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

/**
 * @author vsrivastava
 * @since 10/26/18
 **/
@Mapper(componentModel = "spring")
public interface ProductMappingMapper {

   @Mappings({
      @Mapping(source = "productId", target = "id.productId"),
      @Mapping(source = "productAlias", target = "id.productAlias"),
  })
  ProductMapping toModel(ProductMappingDto dto);


}
