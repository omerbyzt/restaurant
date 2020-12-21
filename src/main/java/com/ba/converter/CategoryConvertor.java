//package com.ba.converter;
//
//import com.ba.dto.CategoryDTO;
//import com.ba.dto.MediaDTO;
//import com.ba.dto.ProductDTO;
//import com.ba.entity.Category;
//import com.ba.entity.Media;
//import com.ba.entity.Product;
//import liquibase.pro.packaged.C;
//import org.springframework.security.core.parameters.P;
//
//import java.util.*;
//
//public class CategoryConvertor {
//
//    public static List<ProductDTO> convertListtoDTOList(List<Product> products){
//        List<ProductDTO> dtoList = new ArrayList<>();
//
//        for(Product product:products){
//            ProductDTO dto = new ProductDTO();
//
//            dto.setProductPrice(product.getProductPrice());
//            dto.setProductName(product.getProductName());
//            dto.setProductDesc(product.getProductDesc());
//            dto.setProductCategory(product.getProductCategory());
//            dto.setProductID(product.getProductID());
//            dto.setCategories(ProductConverter.convertListToDTOList(product.getCategories()));
//            dto.setMediaDTO(MediaConverter.convertMediaToMediaDTO(product.getMedia()));
//
//            dtoList.add(dto);
//        }
//        return dtoList;
//    }
//
//    public static List<Product> convertDTOListToList(List<ProductDTO> productDTOList){
//        List<Product> list = new ArrayList<>();
//
//        for(ProductDTO dto:productDTOList){
//            Product product = new Product();
//
//            product.setProductPrice(dto.getProductPrice());
//            product.setProductCategory(dto.getProductCategory());
//            product.setProductDesc(dto.getProductDesc());
//            product.setProductName(dto.getProductName());
//            product.setProductID(dto.getProductID());
//            product.setCategories(ProductConverter.convertDTOListToList(dto.getCategories()));
//            product.setMedia(MediaConverter.convertMediaDTOToMedia(dto.getMediaDTO()));
//
//            list.add(product);
//        }
//        return list;
//    }
//
//    public static List<CategoryDTO> convertListToCategoryListDTO(List<Category> categoryList){
//
//        List<CategoryDTO> categoryListDTO = new ArrayList<>();
//
//        for(Category categoryListItem : categoryList){
//            CategoryDTO categoryDTO = new CategoryDTO();
//
//            categoryDTO.setDescription(categoryListItem.getDescription());
//            categoryDTO.setId(categoryListItem.getId());
//            categoryDTO.setImageToUrl(categoryListItem.getImageToUrl());
//            categoryDTO.setName(categoryListItem.getName());
//            categoryDTO.setMedia(MediaConverter.convertMediaToMediaDTO(categoryListItem.getMedia()));
//            //categoryDTO.setProducts( CategoryConvertor.convertListtoDTOList(categoryListItem.getProducts()));
//
//            categoryListDTO.add(categoryDTO);
//        }
//
//        return categoryListDTO;
//    }
//
//    public static Category convertDTOtoCategory(CategoryDTO categoryDTO){
//        Category category = new Category();
//
//        //category.setProducts( CategoryConvertor.convertDTOListToList(categoryDTO.getProducts()));
//        category.setImageToUrl(categoryDTO.getImageToUrl());
//        category.setName(categoryDTO.getName());
//        category.setDescription(categoryDTO.getDescription());
//        category.setId(categoryDTO.getId());
//        category.setMedia(MediaConverter.convertMediaDTOToMedia(categoryDTO.getMedia()));
//        return category;
//    }
//
//
//    public static Category convertDTOToCategorywithProducts(CategoryDTO categoryDTO,Category category2){
//        Category category = new Category();
//
//        category.setImageToUrl(categoryDTO.getImageToUrl());
//        category.setName(categoryDTO.getName());
//        category.setDescription(categoryDTO.getDescription());
//        category.setId(categoryDTO.getId());
//        category.setProducts(category2.getProducts());
//        category.setMedia(MediaConverter.convertMediaDTOToMedia(categoryDTO.getMedia()));
//
//        return category;
//    }
//
//    public static List<ProductDTO> convertOptionalCategoryToSetDTO(Optional<Category> category){
//        List<ProductDTO> dto = new ArrayList();
//
//        for(Product prod: category.get().getProducts() ){
//            ProductDTO productDTO = new ProductDTO();
//            productDTO.setProductID(prod.getProductID());
//            //productDTO.setCategory(prod.getCategory());
//            productDTO.setCategories(CategoryConvertor.convertListToCategoryListDTO(prod.getCategories()));
//            productDTO.setProductCategory(prod.getProductCategory());
//            productDTO.setProductDesc(prod.getProductDesc());
//            productDTO.setProductName(prod.getProductName());
//            productDTO.setProductPrice(prod.getProductPrice());
//            productDTO.setMediaDTO(MediaConverter.convertMediaToMediaDTO(prod.getMedia()));
//
//            dto.add(productDTO);
//        }
//        return dto;
//    }
//
//    public static Product convertDTOToProduct(ProductDTO productDTO){
//        Product product = new Product();
//
//        product.setProductID(productDTO.getProductID());
//        product.setProductPrice(productDTO.getProductPrice());
//        product.setProductCategory(productDTO.getProductCategory());
//        product.setProductDesc(productDTO.getProductDesc());
//        product.setProductName(productDTO.getProductName());
//        product.setMedia(MediaConverter.convertMediaDTOToMedia(productDTO.getMediaDTO()));
//
//        return product;
//    }
//
//    //for update
//
//    public static List<Category> updateDTOListToList(List<CategoryDTO> dtoList){
//        List<Category> categoryList = new ArrayList<>();
//
//        dtoList.forEach(dtoListItem -> {
//            Category category = new Category();
//
//            category.setMedia(MediaConverter.convertMediaDTOToMedia(dtoListItem.getMedia()));
//            category.setId(dtoListItem.getId());
//            category.setName(dtoListItem.getName());
//            category.setImageToUrl(dtoListItem.getImageToUrl());
//            category.setDescription(dtoListItem.getDescription());
//
//            categoryList.add(category);
//        });
//
//        return categoryList;
//    }
//
//
//}
