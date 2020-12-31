package com.ba.mapper;

import com.ba.dto.CustomerDTO;
import com.ba.dto.MediaDTO;
import com.ba.entity.Customer;
import com.ba.entity.Media;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2020-12-31T02:24:36+0300",
    comments = "version: 1.3.1.Final, compiler: javac, environment: Java 11.0.9 (Oracle Corporation)"
)
@Component
public class CustomerMapperImpl implements CustomerMapper {

    @Override
    public Customer toEntity(CustomerDTO customerDTO) {
        if ( customerDTO == null ) {
            return null;
        }

        Customer customer = new Customer();

        customer.setId( customerDTO.getId() );
        customer.setName( customerDTO.getName() );
        customer.setSurname( customerDTO.getSurname() );
        customer.setPhoneNumber( customerDTO.getPhoneNumber() );
        customer.setAddress( customerDTO.getAddress() );
        customer.setMedia( mediaDTOToMedia( customerDTO.getMedia() ) );

        return customer;
    }

    @Override
    public CustomerDTO toDTO(Customer customer) {
        if ( customer == null ) {
            return null;
        }

        CustomerDTO customerDTO = new CustomerDTO();

        if ( customer.getId() != null ) {
            customerDTO.setId( customer.getId() );
        }
        customerDTO.setName( customer.getName() );
        customerDTO.setSurname( customer.getSurname() );
        customerDTO.setPhoneNumber( customer.getPhoneNumber() );
        customerDTO.setAddress( customer.getAddress() );
        customerDTO.setMedia( mediaToMediaDTO( customer.getMedia() ) );

        return customerDTO;
    }

    @Override
    public List<Customer> toList(List<CustomerDTO> customerDTOList) {
        if ( customerDTOList == null ) {
            return null;
        }

        List<Customer> list = new ArrayList<Customer>( customerDTOList.size() );
        for ( CustomerDTO customerDTO : customerDTOList ) {
            list.add( toEntity( customerDTO ) );
        }

        return list;
    }

    @Override
    public List<CustomerDTO> toDTOList(List<Customer> customerList) {
        if ( customerList == null ) {
            return null;
        }

        List<CustomerDTO> list = new ArrayList<CustomerDTO>( customerList.size() );
        for ( Customer customer : customerList ) {
            list.add( toDTO( customer ) );
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
