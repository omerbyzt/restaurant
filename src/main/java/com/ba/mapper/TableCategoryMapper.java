package com.ba.mapper;

import com.ba.dto.TableCategoryDTO;
import com.ba.entity.TableCategory;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface TableCategoryMapper {
//    TableCategoryMapper INSTANCE = Mappers.getMapper(TableCategoryMapper.class);

    TableCategory toEntity(TableCategoryDTO tableCategoryDTO);
    TableCategoryDTO toDTO(TableCategory tableCategory);
    List<TableCategory> toList(List<TableCategoryDTO> tableCategoryDTOList);
    List<TableCategoryDTO> toDTOList(List<TableCategory> tableCategoryList);
}
