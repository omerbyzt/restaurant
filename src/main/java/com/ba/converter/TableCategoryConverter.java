//package com.ba.converter;
//
//import com.ba.dto.TableCategoryDTO;
//import com.ba.entity.TableCategory;
//
//import java.util.ArrayList;
//import java.util.List;
//
//public class TableCategoryConverter {
//
//    public static List<TableCategoryDTO> convertDTOListtoList(List<TableCategory> tableCategoryList){
//        List<TableCategoryDTO> tableCategoryListDTO = new ArrayList<>();
//
//        for(TableCategory tableCategoryListItem : tableCategoryList){
//            TableCategoryDTO tableCategoryDTO = new TableCategoryDTO();
//
//            tableCategoryDTO.setId(tableCategoryListItem.getId());
//            tableCategoryDTO.setName(tableCategoryListItem.getName());
//            tableCategoryDTO.setNumber(tableCategoryListItem.getNumber());
//
//            tableCategoryListDTO.add(tableCategoryDTO);
//        }
//
//        return tableCategoryListDTO;
//    }
//
//    public static TableCategory convertDTOToTableCategory(TableCategoryDTO tableCategoryDTO){
//
//        TableCategory tblCategory = new TableCategory();
//        tblCategory.setId(tableCategoryDTO.getId());
//        tblCategory.setName(tableCategoryDTO.getName());
//        tblCategory.setNumber(tableCategoryDTO.getNumber());
//
//        return tblCategory;
//    }
//
//
//}
