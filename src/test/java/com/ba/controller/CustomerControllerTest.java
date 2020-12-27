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
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.data.domain.*;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class CustomerControllerTest {

    @InjectMocks
    private CustomerController customerController;

    @Mock
    private CustomerService customerService;

    @Mock
    private CustomerRepository customerRepository;

    @Mock
    private CustomerMapper customerMapper;

    @Mock
    private BusinessRuleException businessRuleException;

    private Customer customer = new Customer();
    private CustomerDTO customerDTO = new CustomerDTO();
    private CustomerDTO customerDTOWOId = new CustomerDTO();
    private List<Customer> customerList = new ArrayList<>();
    private List<CustomerDTO> customerDTOList = new ArrayList<>();
    
    private Media media = new Media();
    private MediaDTO mediaDTO = new MediaDTO();

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
    public void shouldAddCustomer() {
        when(customerService.addCustomer(customerDTO)).thenReturn("Customer Added");
        String res = customerController.addCustomer(customerDTO);
        assertEquals(res,"Customer Added");
    }

    @Test(expected = BusinessRuleException.class)
    public void shouldNotAddCustomer() {
        String res = customerController.addCustomer(null);
    }

    @Test
    public void shouldDeleteCustomer() {
        when(customerService.deleteCustomer(id)).thenReturn("Customer Deleted");
        String res = customerController.deleteCustomer(id);
        assertEquals(res,"Customer Deleted");
    }

    @Test(expected = BusinessRuleException.class)
    public void shouldNotDeleteCustomer() {
        String res = customerController.deleteCustomer(null);
    }

    @Test
    public void shouldUpdateCustomer() {
        when(customerService.updateCustomer(customerDTO)).thenReturn(customerDTO);
        CustomerDTO tempCustomerDTO = customerController.updateCustomer(customerDTO);
        assertEquals(tempCustomerDTO,customerDTO);
    }

    @Test(expected = BusinessRuleException.class)
    public void shouldNotUpdateCustomer() {
        CustomerDTO tempCustomerDTO = customerController.updateCustomer(null);
    }

    @Test
    public void shouldGetCustomerById() {
        when(customerService.getCustomerById(id)).thenReturn(customerDTO);
        CustomerDTO tempCustomerDTO = customerController.getCustomerById(id);
        assertEquals(customerDTO,tempCustomerDTO);
    }

    @Test(expected = BusinessRuleException.class)
    public void shouldNotGetCustomerById() {
        CustomerDTO tempCustomerDTO = customerController.getCustomerById(null);
    }

    @Test
    public void shouldGetCustomersBySlice() {
        Slice<CustomerDTO> customerDTOSlice = new SliceImpl<CustomerDTO>(dtoList);
        when(customerController.getCustomersBySlice(0,10)).thenReturn(customerDTOSlice);
        Slice<CustomerDTO> tempCustomerDTOSlice = customerService.getCustomersBySlice(pageable);

        assertNotNull(tempCustomerDTOSlice);
        assertEquals(tempCustomerDTOSlice,customerDTOSlice);
    }

    @Test
    public void shouldGetCustomersByPage() {
        Page<CustomerDTO> customerDTOPage = new PageImpl<CustomerDTO>(dtoList);
        when(customerController.getCustomersByPage(0,10)).thenReturn(customerDTOPage);
        Page<CustomerDTO> tempCustomerDTOPage = customerService.getCustomersByPage(pageable);

        assertNotNull(tempCustomerDTOPage);
        assertEquals(tempCustomerDTOPage,customerDTOPage);
    }
}