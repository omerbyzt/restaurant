package com.ba.mapper;

import com.ba.dto.ProductDTO;
import com.ba.entity.Product;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import org.springframework.data.domain.Page;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ProductMapper {
//    ProductMapper INSTANCE = Mappers.getMapper(ProductMapper.class);

    @Mapping(ignore = true,source = "categories", target = "categories")
    @Mapping(source = "mediaDTO" ,target = "media")
    Product toEntity(ProductDTO productDTO);

    @Mapping(ignore = true,source = "categories", target = "categories")
    @Mapping(source = "media" ,target = "mediaDTO")
    ProductDTO toDTO(Product product);

    List<Product> toList(List<ProductDTO> productDTOList);

    List<ProductDTO> toDTOList(List<Product> productList);

}
