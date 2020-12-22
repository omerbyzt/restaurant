package com.ba.mapper;

import com.ba.dto.CategoryDTO;
import com.ba.entity.Category;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2020-12-22T19:22:18+0300",
    comments = "version: 1.3.1.Final, compiler: javac, environment: Java 11.0.9 (Oracle Corporation)"
)
public class CategoryMapperImpl implements CategoryMapper {

    @Override
    public Category toEntity(CategoryDTO categoryDTO) {
        if ( categoryDTO == null ) {
            return null;
        }

        Category category = new Category();

        category.setId( categoryDTO.getId() );
        category.setName( categoryDTO.getName() );
        category.setDescription( categoryDTO.getDescription() );
        category.setImageToUrl( categoryDTO.getImageToUrl() );
        category.setDeleted( categoryDTO.isDeleted() );

        return category;
    }

    @Override
    public CategoryDTO toDTO(Category category) {
        if ( category == null ) {
            return null;
        }

        CategoryDTO categoryDTO = new CategoryDTO();

        categoryDTO.setId( category.getId() );
        categoryDTO.setName( category.getName() );
        categoryDTO.setDescription( category.getDescription() );
        categoryDTO.setImageToUrl( category.getImageToUrl() );
        categoryDTO.setDeleted( category.isDeleted() );

        return categoryDTO;
    }

    @Override
    public List<Category> toList(List<CategoryDTO> categoryDTOList) {
        if ( categoryDTOList == null ) {
            return null;
        }

        List<Category> list = new ArrayList<Category>( categoryDTOList.size() );
        for ( CategoryDTO categoryDTO : categoryDTOList ) {
            list.add( toEntity( categoryDTO ) );
        }

        return list;
    }

    @Override
    public List<CategoryDTO> toDTOList(List<Category> categoryList) {
        if ( categoryList == null ) {
            return null;
        }

        List<CategoryDTO> list = new ArrayList<CategoryDTO>( categoryList.size() );
        for ( Category category : categoryList ) {
            list.add( toDTO( category ) );
        }

        return list;
    }
}
