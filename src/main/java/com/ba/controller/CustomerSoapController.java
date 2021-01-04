package com.ba.controller;

import com.ba.dto.CustomerDTO;
import com.ba.service.CustomerSoapService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/soap")
public class CustomerSoapController {

    @Autowired
    private CustomerSoapService customerSoapService;

    @DeleteMapping("/{id}")
    public boolean deleteCustomer(@PathVariable Long id) {
        return customerSoapService.deleteCustomer(id);
    }

    @PostMapping
    public boolean addCustomer(@RequestBody CustomerDTO customerDTO){
        return customerSoapService.addCustomer(customerDTO);
    }

    @PutMapping
    public boolean updateCustomer(@RequestBody CustomerDTO customerDTO){
        return customerSoapService.updateCustomer(customerDTO);
    }

    @GetMapping
    public List<CustomerDTO> getAllCustomer(){
        return customerSoapService.getAllCustomer();
    }
}
