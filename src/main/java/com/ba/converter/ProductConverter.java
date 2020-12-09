package com.ba.converter;

import com.ba.dto.ProductDTO;
import com.ba.entity.Product;

import java.util.ArrayList;
import java.util.List;

public class ProductConverter {

    public static List<ProductDTO> convertProductToProductDTO(List<Product> productList){

        List<ProductDTO> productListDTO = new ArrayList<>();

        for (Product productListItem : productList){
            ProductDTO productDTO = new ProductDTO();

            productDTO.setCategories(productListItem.getCategories());
            //productDTO.setCategory(productListItem.getCategory());
            productDTO.setProductCategory(productListItem.getProductCategory());
            productDTO.setProductDesc(productListItem.getProductDesc());
            productDTO.setProductID(productListItem.getProductID());
            productDTO.setProductName(productListItem.getProductName());
            productDTO.setProductPrice(productListItem.getProductPrice());

            productListDTO.add(productDTO);
        }
        return productListDTO;
    }

    public static Product convertDTOToProduct(ProductDTO productDTO){
        Product product = new Product();

        product.setCategories(productDTO.getCategories());
        //product.setCategory(productDTO.getCategory());
        product.setProductName(productDTO.getProductName());
        product.setProductDesc(productDTO.getProductDesc());
        product.setProductCategory(productDTO.getProductCategory());
        product.setProductPrice(productDTO.getProductPrice());
        product.setProductID(productDTO.getProductID());

        return product;
    }


}
