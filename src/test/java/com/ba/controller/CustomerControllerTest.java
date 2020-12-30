package com.ba.controller;

import com.ba.builder.CustomerBuilder;
import com.ba.builder.CustomerDTOBuilder;
import com.ba.builder.MediaBuilder;
import com.ba.builder.MediaDTOBuilder;
import com.ba.dto.CustomerDTO;
import com.ba.dto.MediaDTO;
import com.ba.entity.Customer;
import com.ba.entity.Media;
import com.ba.exception.BusinessRuleException;
import com.ba.mapper.CustomerMapper;
import com.ba.repository.CustomerRepository;
import com.ba.service.CustomerService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.data.domain.*;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class CustomerControllerTest {

    @InjectMocks
    private CustomerController customerController;

    @Mock
    private CustomerService customerService;

    @Mock
    private CustomerMapper customerMapper;

    private Customer customer;
    private CustomerDTO customerDTO;
    private List<Customer> customerList = new ArrayList<>();
    private List<CustomerDTO> customerDTOList = new ArrayList<>();
    
    private Media media;
    private MediaDTO mediaDTO;

    private Long id = 1L;

    private Pageable pageable = PageRequest.of(0,10);

    private List<CustomerDTO> dtoList = new ArrayList<>();

    @Before
    public void setUp() throws Exception {
        media = new MediaBuilder().id(1L).name("mediaNameTest").fileContent(null).build();
        mediaDTO = new MediaDTOBuilder().id(1L).name("mediaNameTest").fileContent(null).build();
        
        customer = new CustomerBuilder().id(1L).name("customerNameTest").surname("customerSurnameTest").phoneNumber("123Test").address("addressTest").media(media).build();
        customerDTO = new CustomerDTOBuilder().id(1L).name("customerNameTest").surname("customerSurnameTest").phoneNumber("123Test").address("addressTest").media(mediaDTO).build();

        customerList.add(customer);
        customerDTOList.add(customerDTO);

        dtoList.add(customerDTO);
    }

    @Test
    public void shouldListCustomers() {
        when(customerService.listCustomers()).thenReturn(customerDTOList);
        List<CustomerDTO> result = customerController.listCustomers();
        Mockito.verify(customerService).listCustomers();

        assertNotNull(result);
    }

//    @Test
//    public void shouldAddCustomer() {
//        when(customerService.addCustomer(customerDTO)).thenReturn("Customer Added");
//        String res = customerController.addCustomer(customerDTO);
//        assertEquals(res,"Customer Added");
//    }

    @Test
    public void shouldDeleteCustomer() {
        when(customerService.deleteCustomer(id)).thenReturn("Customer Deleted");
        String res = customerController.deleteCustomer(id);
        verify(customerService).deleteCustomer(id);
        assertEquals(res,"Customer Deleted");
    }

    @Test(expected = BusinessRuleException.class)
    public void shouldNotDeleteCustomerWhenIdNull() {
        customerController.deleteCustomer(null);
    }

    @Test
    public void shouldUpdateCustomer() {
        when(customerService.updateCustomer(customerDTO)).thenReturn(customerDTO);
        CustomerDTO tempCustomerDTO = customerController.updateCustomer(customerDTO);
        assertEquals(tempCustomerDTO,customerDTO);
    }

    @Test
    public void shouldGetCustomerById() {
        when(customerService.getCustomerById(id)).thenReturn(customerDTO);
        CustomerDTO tempCustomerDTO = customerController.getCustomerById(id);
        assertEquals(customerDTO,tempCustomerDTO);
    }

    @Test(expected = BusinessRuleException.class)
    public void shouldNotGetCustomerById() {
        customerController.getCustomerById(null);
    }

    @Test
    public void shouldGetCustomersBySlice() {
        Slice<CustomerDTO> customerDTOSlice = new SliceImpl<CustomerDTO>(dtoList);
        when(customerService.getCustomersBySlice(pageable)).thenReturn(customerDTOSlice);
        Slice<CustomerDTO> result = customerController.getCustomersBySlice(0,10);

        assertNotNull(result);
        assertEquals(result,customerDTOSlice);
    }

    @Test
    public void shouldGetCustomersByPage() {
        Page<CustomerDTO> customerDTOPage = new PageImpl<CustomerDTO>(dtoList);
        when(customerService.getCustomersByPage(pageable)).thenReturn(customerDTOPage);
        Page<CustomerDTO> result = customerController.getCustomersByPage(0,10);

        assertNotNull(result);
        assertEquals(result,customerDTOPage);
    }
}