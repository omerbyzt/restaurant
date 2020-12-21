package com.ba.mapper;

import com.ba.dto.CategoryDTO;
import com.ba.dto.MediaDTO;
import com.ba.entity.Category;
import com.ba.entity.Media;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.annotation.processing.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2020-12-21T22:22:33+0300",
    comments = "version: 1.3.1.Final, compiler: javac, environment: Java 11.0.9 (Oracle Corporation)"
)
public class CategoryMapperImpl implements CategoryMapper {

    @Override
    public Category toEntity(CategoryDTO categoryDTO) {
        if ( categoryDTO == null ) {
            return null;
        }

        Category category = new Category();

        category.setMedia( mediaDTOToMedia( categoryDTO.getMedia() ) );
        category.setId( categoryDTO.getId() );
        category.setName( categoryDTO.getName() );
        category.setDescription( categoryDTO.getDescription() );
        category.setImageToUrl( categoryDTO.getImageToUrl() );

        return category;
    }

    @Override
    public CategoryDTO toDTO(Category category) {
        if ( category == null ) {
            return null;
        }

        CategoryDTO categoryDTO = new CategoryDTO();

        categoryDTO.setMedia( mediaToMediaDTO( category.getMedia() ) );
        categoryDTO.setId( category.getId() );
        categoryDTO.setName( category.getName() );
        categoryDTO.setDescription( category.getDescription() );
        categoryDTO.setImageToUrl( category.getImageToUrl() );

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

    protected Media mediaDTOToMedia(MediaDTO mediaDTO) {
        if ( mediaDTO == null ) {
            return null;
        }

        Media media = new Media();

        media.setId( mediaDTO.getId() );
        media.setName( mediaDTO.getName() );
        byte[] fileContent = mediaDTO.getFileContent();
        if ( fileContent != null ) {
            media.setFileContent( Arrays.copyOf( fileContent, fileContent.length ) );
        }

        return media;
    }

    protected MediaDTO mediaToMediaDTO(Media media) {
        if ( media == null ) {
            return null;
        }

        MediaDTO mediaDTO = new MediaDTO();

        mediaDTO.setId( media.getId() );
        mediaDTO.setName( media.getName() );
        byte[] fileContent = media.getFileContent();
        if ( fileContent != null ) {
            mediaDTO.setFileContent( Arrays.copyOf( fileContent, fileContent.length ) );
        }

        return mediaDTO;
    }
}
