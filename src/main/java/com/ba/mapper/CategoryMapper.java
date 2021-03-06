package com.ba.mapper;

import com.ba.dto.CategoryDTO;
import com.ba.entity.Category;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CategoryMapper {

//    CategoryMapper INSTANCE = Mappers.getMapper(CategoryMapper.class);

    @Mapping(ignore = true, source = "products", target = "products")
    @Mapping(source = "mediaDTO" , target = "media")
    Category toEntity(CategoryDTO categoryDTO);

    @Mapping(ignore = true, source = "products", target = "products")
    @Mapping(source = "media" , target = "mediaDTO")
    CategoryDTO toDTO(Category category);

    List<Category> toList(List<CategoryDTO> categoryDTOList);

    List<CategoryDTO> toDTOList(List<Category> categoryList);
}
