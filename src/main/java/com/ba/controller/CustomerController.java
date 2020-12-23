package com.ba.controller;

import com.ba.dto.CustomerDTO;
import com.ba.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/customer")
@CrossOrigin(origins = "*")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @GetMapping
    public List<CustomerDTO> listCustomers(){
        return customerService.listCustomers();
    }

    @PostMapping
    public String addCustomer(@RequestBody CustomerDTO customerDTO){
        if(customerDTO == null){
            return null;
        }
        return customerService.addCustomer(customerDTO);
    }

    @DeleteMapping("/{id}")
    public String deleteCustomer(@PathVariable Long id){
        if(id == null){
            return null;
        }
        return customerService.deleteCustomer(id);
    }

    @PutMapping
    public String updateCustomer(@RequestBody CustomerDTO customerDTO){
        if(customerDTO == null){
            return null;
        }
        return customerService.updateCustomer(customerDTO);
    }

    @GetMapping("/{id}")
    public CustomerDTO getCustomerById(@PathVariable Long id){
        if(id == null){
            return null;
        }
        return customerService.getCustomerById(id);
    }

    @GetMapping("/slice")
    public Slice<CustomerDTO> getCustomersBySlice(@RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "20") Integer size){
        if(page == null || size == null){
            return null;
        }
        Pageable pageable = PageRequest.of(page,size);
        return customerService.getCustomersBySlice(pageable);
    }

    @GetMapping("/page")
    public Page<CustomerDTO> getCustomersByPage(@RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "20") Integer size){
        if(page == null || size == null){
            return null;
        }
        Pageable pageable = PageRequest.of(page,size);
        return customerService.getCustomersByPage(pageable);
    }
}
