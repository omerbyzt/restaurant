package com.ba.mapper;

import com.ba.dto.MediaDTO;
import com.ba.dto.ProductDTO;
import com.ba.entity.Media;
import com.ba.entity.Product;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.annotation.processing.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2020-12-25T23:13:42+0300",
    comments = "version: 1.3.1.Final, compiler: javac, environment: Java 11.0.9 (Oracle Corporation)"
)
public class ProductMapperImpl implements ProductMapper {

    @Override
    public Product toEntity(ProductDTO productDTO) {
        if ( productDTO == null ) {
            return null;
        }

        Product product = new Product();

        product.setMedia( mediaDTOToMedia( productDTO.getMediaDTO() ) );
        product.setId( productDTO.getId() );
        product.setProductName( productDTO.getProductName() );
        product.setProductDesc( productDTO.getProductDesc() );
        product.setProductCategory( productDTO.getProductCategory() );
        product.setProductPrice( productDTO.getProductPrice() );
        product.setDeleted( productDTO.isDeleted() );

        return product;
    }

    @Override
    public ProductDTO toDTO(Product product) {
        if ( product == null ) {
            return null;
        }

        ProductDTO productDTO = new ProductDTO();

        productDTO.setMediaDTO( mediaToMediaDTO( product.getMedia() ) );
        productDTO.setId( product.getId() );
        productDTO.setProductName( product.getProductName() );
        productDTO.setProductDesc( product.getProductDesc() );
        productDTO.setProductCategory( product.getProductCategory() );
        productDTO.setProductPrice( product.getProductPrice() );
        productDTO.setDeleted( product.isDeleted() );

        return productDTO;
    }

    @Override
    public List<Product> toList(List<ProductDTO> productDTOList) {
        if ( productDTOList == null ) {
            return null;
        }

        List<Product> list = new ArrayList<Product>( productDTOList.size() );
        for ( ProductDTO productDTO : productDTOList ) {
            list.add( toEntity( productDTO ) );
        }

        return list;
    }

    @Override
    public List<ProductDTO> toDTOList(List<Product> productList) {
        if ( productList == null ) {
            return null;
        }

        List<ProductDTO> list = new ArrayList<ProductDTO>( productList.size() );
        for ( Product product : productList ) {
            list.add( toDTO( product ) );
        }

        return list;
    }

    protected Media mediaDTOToMedia(MediaDTO mediaDTO) {
        if ( mediaDTO == null ) {
            return null;
        }

        Media media = new Media();

        media.setId( mediaDTO.getId() );
        media.setDeleted( mediaDTO.isDeleted() );
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
        mediaDTO.setDeleted( media.isDeleted() );

        return mediaDTO;
    }
}
