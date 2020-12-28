package com.ba.service;

import com.ba.builder.CustomerBuilder;
import com.ba.builder.CustomerDTOBuilder;
import com.ba.builder.MediaBuilder;
import com.ba.builder.MediaDTOBuilder;
import com.ba.dto.CustomerDTO;
import com.ba.dto.MediaDTO;
import com.ba.entity.Customer;
import com.ba.entity.Media;
import com.ba.exception.SystemException;
import com.ba.mapper.CustomerMapper;
import com.ba.repository.CustomerRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.internal.verification.VerificationModeFactory.times;

@RunWith(MockitoJUnitRunner.class)
public class CustomerServiceTest {

    @InjectMocks
    private CustomerService customerService;

    @Mock
    private CustomerRepository customerRepository;

    @Mock
    private CustomerMapper customerMapper;

    private Customer customer = new Customer();
    private CustomerDTO customerDTO = new CustomerDTO();
    private CustomerDTO customerDTOWOId = new CustomerDTO();
    private List<Customer> customerList = new ArrayList<>();
    private List<CustomerDTO> customerDTOList = new ArrayList<>();

    private Media media = new Media();
    private MediaDTO mediaDTO = new MediaDTO();

    private Long id = 1L;

    @Before
    public void setUp() throws Exception {
        media = new MediaBuilder().id(1L).name("mediaNameTest").fileContent(null).build();
        mediaDTO = new MediaDTOBuilder().id(1L).name("mediaNameTest").fileContent(null).build();

        customer = new CustomerBuilder().id(1L).name("customerNameTest").surname("customerSurnameTest").phoneNumber("123Test").address("addressTest").media(media).build();
        customerDTO = new CustomerDTOBuilder().id(1L).name("customerNameTest").surname("customerSurnameTest").phoneNumber("123Test").address("addressTest").media(mediaDTO).build();

        customerList.add(customer);
        customerDTOList.add(customerDTO);
    }

    @Test
    public void shouldAddCustomer() {
        when(customerRepository.save(customerMapper.toEntity(customerDTO))).thenReturn(customer);
        String res = customerService.addCustomer(customerDTO);
        verify(customerRepository).save(customer);
        assertEquals(res,"Customer Added");
    }

    @Test
    public void shouldDeleteCustomer() {
        String res = customerService.deleteCustomer(id);
        assertEquals(res,"Customer Deleted");
        verify(customerRepository,times(1)).deleteById(id);
    }

    @Test
    public void shouldUpdateCustomer() {
        when(customerRepository.findById(id)).thenReturn(Optional.of(customer));
        when(customerRepository.saveAndFlush(customerMapper.toEntity(customerDTO))).thenReturn(customer);
        when(customerMapper.toDTO(customer)).thenReturn(customerDTO);

        CustomerDTO tempCustomerDTO = customerService.updateCustomer(customerDTO);
        verify(customerRepository).saveAndFlush(customer);
        assertNotNull(tempCustomerDTO);
        assertEquals(tempCustomerDTO,customerDTO);
    }

    @Test(expected = SystemException.class)
    public void shouldNotUpdateCustomer() {
        when(customerRepository.saveAndFlush(customerMapper.toEntity(customerDTO))).thenReturn(customer);
        CustomerDTO tempCustomerDTO = customerService.updateCustomer(customerDTO);
    }
}