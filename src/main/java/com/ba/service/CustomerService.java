package com.ba.service;

import com.ba.dto.CustomerDTO;
import com.ba.entity.Customer;
import com.ba.exception.SystemException;
import com.ba.helper.UpdateHelper;
import com.ba.mapper.CustomerMapper;
import com.ba.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private CustomerMapper customerMapper;

    public String addCustomer(CustomerDTO customerDTO) {
        customerRepository.save(customerMapper.toEntity(customerDTO));
        return "Customer Added";
    }

    public String deleteCustomer(Long id) {
        customerRepository.deleteById(id);
        return "Customer Deleted";
    }

    public CustomerDTO updateCustomer(CustomerDTO customerDTO) {
        Optional<Customer> customer = customerRepository.findById(customerDTO.getId());
        if (customer == null) {
            throw new SystemException("Customer not found..!");
        }

        UpdateHelper.customerSetCheck(customerDTO, customer);

        customerRepository.saveAndFlush(customer.get());
        return customerMapper.toDTO(customer.get());
    }

    public CustomerDTO getCustomerById(Long id) {
        Optional<Customer> customer = customerRepository.findById(id);
        if (customer.isEmpty()) {
            throw new SystemException("Customers not found...!");
        }
        CustomerDTO customerDTO = customerMapper.toDTO(customer.get());
        return customerDTO;
    }

    public Slice<CustomerDTO> getCustomersBySlice(Pageable pageable) {
        Slice<CustomerDTO> customerDTOSlice = customerRepository.findBy(pageable).map(customerMapper::toDTO);
        if (customerDTOSlice.isEmpty()) {
            throw new SystemException("Customers not found...!");
        }
        return customerDTOSlice;
    }

    public Page<CustomerDTO> getCustomersByPage(Pageable pageable) {
        Page<CustomerDTO> customerDTOPage = customerRepository.findAll(pageable).map(customerMapper::toDTO);
        if (customerDTOPage.isEmpty()) {
            throw new SystemException("Customers not found...!");
        }
        return customerDTOPage;
    }
}
