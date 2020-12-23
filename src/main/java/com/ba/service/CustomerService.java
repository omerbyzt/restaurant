package com.ba.service;

import com.ba.dto.CustomerDTO;
import com.ba.entity.Customer;
import com.ba.mapper.CustomerMapper;
import com.ba.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private CustomerMapper customerMapper;

    public List<CustomerDTO> listCustomers() {
        List<Customer> customerList = customerRepository.findAll();
        if (customerList.isEmpty()) {
            return null;
        }
        return customerMapper.toDTOList(customerList);
    }

    public String addCustomer(CustomerDTO customerDTO) {
        customerRepository.save(customerMapper.toEntity(customerDTO));
        return "Customer Added";
    }

    public String deleteCustomer(Long id) {
        customerRepository.deleteById(id);
        return "Customer Deleted";
    }

    public String updateCustomer(CustomerDTO customerDTO) {
        customerRepository.saveAndFlush(customerMapper.toEntity(customerDTO));
        return "Customer Updated";
    }

    public CustomerDTO getCustomerById(Long id) {
        Optional<Customer> customer = customerRepository.findById(id);
        if (!customer.isPresent()) {
            return null;
        }
        CustomerDTO customerDTO = customerMapper.toDTO(customer.get());
        return customerDTO;
    }

    public Slice<CustomerDTO> getCustomersBySlice(Pageable pageable) {
        Slice<CustomerDTO> customerDTOSlice = customerRepository.findBy(pageable).map(customerMapper::toDTO);
        if(customerDTOSlice.isEmpty()){
            return null;
        }
        return customerDTOSlice;
    }

    public Page<CustomerDTO> getCustomersByPage(Pageable pageable) {
        Page<CustomerDTO> customerDTOPage = customerRepository.findAll(pageable).map(customerMapper::toDTO);
        if(customerDTOPage.isEmpty()){
            return null;
        }
        return customerDTOPage;
    }
}
