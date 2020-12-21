//package com.ba.converter;
//
//import com.ba.dto.CategoryDTO;
//import com.ba.dto.ProductDTO;
//import com.ba.entity.Category;
//import com.ba.entity.Product;
//import org.springframework.security.core.parameters.P;
//
//import java.util.ArrayList;
//import java.util.List;
//
//public class ProductConverter {
//    public static List<Category> convertDTOListToList(List<CategoryDTO> categoryDTOList){
//        List<Category> list = new ArrayList<>();
//
//        for(CategoryDTO dto: categoryDTOList){
//            Category category = new Category();
//            category.setId(dto.getId());
//            category.setName(dto.getName());
//            category.setDescription(dto.getDescription());
//            category.setImageToUrl(dto.getImageToUrl());
//            category.setProducts(CategoryConvertor.convertDTOListToList(dto.getProducts()));
//
//            list.add(category);
//        }
//        return list;
//    }
//
//    public static List<CategoryDTO> convertListToDTOList(List<Category> categoryList){
//        List<CategoryDTO> dtoList = new ArrayList<>();
//
//        for(Category category: categoryList){
//            CategoryDTO dto = new CategoryDTO();
//
//            dto.setId(category.getId());
//            dto.setName(category.getName());
//            dto.setDescription(category.getDescription());
//            dto.setImageToUrl(category.getImageToUrl());
//            dto.setProducts(CategoryConvertor.convertListtoDTOList(category.getProducts()));
//
//            dtoList.add(dto);
//        }
//        return dtoList;
//    }
//
//    public static List<ProductDTO> convertProductToProductDTO(List<Product> productList){
//
//        List<ProductDTO> productListDTO = new ArrayList<>();
//
//        for (Product productListItem : productList){
//            ProductDTO productDTO = new ProductDTO();
//
//            productDTO.setProductCategory(productListItem.getProductCategory());
//            productDTO.setProductDesc(productListItem.getProductDesc());
//            productDTO.setProductID(productListItem.getProductID());
//            productDTO.setProductName(productListItem.getProductName());
//            productDTO.setProductPrice(productListItem.getProductPrice());
//            productDTO.setMediaDTO(MediaConverter.convertMediaToMediaDTO(productListItem.getMedia()));
//            //TODO : Patlaması halinde uçurunuz
//            productDTO.setCategories(CategoryConvertor.convertListToCategoryListDTO(productListItem.getCategories()));
//            //
//            productListDTO.add(productDTO);
//        }
//        return productListDTO;
//    }
//
//    public static Product convertDTOToProduct(ProductDTO productDTO){
//        Product product = new Product();
//
//        product.setCategories(ProductConverter.convertDTOListToList(productDTO.getCategories()));
//        product.setProductName(productDTO.getProductName());
//        product.setProductDesc(productDTO.getProductDesc());
//        product.setProductCategory(productDTO.getProductCategory());
//        product.setProductPrice(productDTO.getProductPrice());
//        product.setProductID(productDTO.getProductID());
//        product.setMedia(MediaConverter.convertMediaDTOToMedia(productDTO.getMediaDTO()));
//
//        return product;
//    }
//
//    //Update için yeniden yazılan converterlar
//
//    public static Product updateDTOToEntity(ProductDTO productDTO){
//        Product product = new Product();
//
//        product.setProductID(productDTO.getProductID());
//        product.setProductName(productDTO.getProductName());
//        product.setProductDesc(productDTO.getProductDesc());
//        product.setProductCategory(productDTO.getProductCategory());
//        product.setProductPrice(productDTO.getProductPrice());
//        product.setMedia(MediaConverter.convertMediaDTOToMedia(productDTO.getMediaDTO()));
//
//        product.getCategories().addAll(CategoryConvertor.updateDTOListToList(productDTO.getCategories()));
//
//        return product;
//    }
//
//
//}
