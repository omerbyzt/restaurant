package com.ba.mapper;

import com.ba.dto.CustomerDTO;
import com.ba.entity.Customer;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2020-12-26T16:09:08+0300",
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
        customer.setDeleted( customerDTO.isDeleted() );
        customer.setName( customerDTO.getName() );
        customer.setSurname( customerDTO.getSurname() );
        customer.setPhoneNumber( customerDTO.getPhoneNumber() );
        customer.setAddress( customerDTO.getAddress() );

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
        customerDTO.setDeleted( customer.isDeleted() );

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
}
