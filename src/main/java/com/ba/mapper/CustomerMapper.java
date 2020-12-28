package com.ba.mapper;

import com.ba.dto.CustomerDTO;
import com.ba.entity.Customer;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CustomerMapper {

    Customer toEntity(CustomerDTO customerDTO);
    CustomerDTO toDTO(Customer customer);

    List<Customer> toList(List<CustomerDTO> customerDTOList);
    List<CustomerDTO> toDTOList(List<Customer> customerList);
}
