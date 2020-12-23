package com.ba.mapper;

import com.ba.dto.TableCategoryDTO;
import com.ba.entity.TableCategory;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2020-12-23T20:12:24+0300",
    comments = "version: 1.3.1.Final, compiler: javac, environment: Java 11.0.9 (Oracle Corporation)"
)
public class TableCategoryMapperImpl implements TableCategoryMapper {

    @Override
    public TableCategory toEntity(TableCategoryDTO tableCategoryDTO) {
        if ( tableCategoryDTO == null ) {
            return null;
        }

        TableCategory tableCategory = new TableCategory();

        tableCategory.setId( tableCategoryDTO.getId() );
        tableCategory.setName( tableCategoryDTO.getName() );
        tableCategory.setNumber( tableCategoryDTO.getNumber() );
        tableCategory.setDeleted( tableCategoryDTO.isDeleted() );

        return tableCategory;
    }

    @Override
    public TableCategoryDTO toDTO(TableCategory tableCategory) {
        if ( tableCategory == null ) {
            return null;
        }

        TableCategoryDTO tableCategoryDTO = new TableCategoryDTO();

        tableCategoryDTO.setId( tableCategory.getId() );
        tableCategoryDTO.setName( tableCategory.getName() );
        tableCategoryDTO.setNumber( tableCategory.getNumber() );
        tableCategoryDTO.setDeleted( tableCategory.isDeleted() );

        return tableCategoryDTO;
    }

    @Override
    public List<TableCategory> toList(List<TableCategoryDTO> tableCategoryDTOList) {
        if ( tableCategoryDTOList == null ) {
            return null;
        }

        List<TableCategory> list = new ArrayList<TableCategory>( tableCategoryDTOList.size() );
        for ( TableCategoryDTO tableCategoryDTO : tableCategoryDTOList ) {
            list.add( toEntity( tableCategoryDTO ) );
        }

        return list;
    }

    @Override
    public List<TableCategoryDTO> toDTOList(List<TableCategory> tableCategoryList) {
        if ( tableCategoryList == null ) {
            return null;
        }

        List<TableCategoryDTO> list = new ArrayList<TableCategoryDTO>( tableCategoryList.size() );
        for ( TableCategory tableCategory : tableCategoryList ) {
            list.add( toDTO( tableCategory ) );
        }

        return list;
    }
}
