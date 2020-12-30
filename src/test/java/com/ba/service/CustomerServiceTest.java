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
import com.ba.mapper.MediaMapper;
import com.ba.repository.CustomerRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.data.domain.*;

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

    @Mock
    private MediaMapper mediaMapper;

    private Customer customer;
    private CustomerDTO customerDTO;
    private List<Customer> customerList = new ArrayList<>();
    private List<CustomerDTO> customerDTOList = new ArrayList<>();

    private Media media;
    private MediaDTO mediaDTO;

    private Long id = 1L;

    private Pageable pageable = PageRequest.of(0,10);

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
    public void shouldListCustomers() {
        when(customerRepository.findAll()).thenReturn(customerList);
        List<Customer> result = customerMapper.toList(customerService.listCustomers());
        verify(customerRepository).findAll();
        assertNotNull(result);
    }

    @Test(expected = SystemException.class)
    public void shouldThrowSysExceptionWhenResultNullListCustomer() {
        when(customerRepository.findAll()).thenReturn(null);
        customerService.listCustomers();
    }

//    @Test
//    public void shouldAddCustomer() {
//        when(customerRepository.save(customerMapper.toEntity(customerDTO))).thenReturn(customer);
//        when(customerMapper.toEntity(customerDTO)).thenReturn(customer);
//
//        String res = customerService.addCustomer(customerDTO);
//        verify(customerRepository).save(customer);
//        assertEquals(res,"Customer Added");
//    }

    @Test
    public void shouldDeleteCustomer() {
        String res = customerService.deleteCustomer(id);
        verify(customerRepository,times(1)).deleteById(id);
        assertEquals(res,"Customer Deleted");
    }

    @Test
    public void shouldUpdateCustomer() {
        when(customerRepository.findById(id)).thenReturn(Optional.of(customer));
        when(customerRepository.saveAndFlush(customerMapper.toEntity(customerDTO))).thenReturn(customer);
        when(customerMapper.toDTO(customer)).thenReturn(customerDTO);
        when(mediaMapper.toEntity(mediaDTO)).thenReturn(media);

        CustomerDTO tempCustomerDTO = customerService.updateCustomer(customerDTO);
        verify(customerRepository).saveAndFlush(customer);
        assertNotNull(tempCustomerDTO);
        assertEquals(tempCustomerDTO,customerDTO);
    }

    @Test(expected = SystemException.class)
    public void shouldNotUpdateCustomer() {
        when(customerRepository.saveAndFlush(customerMapper.toEntity(customerDTO))).thenReturn(customer);
        customerService.updateCustomer(customerDTO);
    }

    @Test
    public void shouldGetCustomerById() {
        when(customerRepository.findById(id)).thenReturn(Optional.of(customer));
        when(customerMapper.toDTO(customer)).thenReturn(customerDTO);

        CustomerDTO result = customerService.getCustomerById(id);
        verify(customerRepository).findById(id);
        assertNotNull(result);
    }

    @Test(expected = SystemException.class)
    public void shouldThrowSysExceptionWhenIdNullGetCustomerById() {
        customerService.getCustomerById(id);
    }

    @Test
    public void shouldGetCustomerBySlice() {
        Slice<Customer> customerSlice = new SliceImpl<>(customerList);
        when(customerRepository.findBy(pageable)).thenReturn(customerSlice);
        when(customerMapper.toDTO(customer)).thenReturn(customerDTO);

        Slice<CustomerDTO> customerDTOSlice = customerSlice.map(customerMapper::toDTO);
        Slice<CustomerDTO> result= customerService.getCustomersBySlice(pageable);

        assertNotNull(result);
        assertEquals(result,customerDTOSlice);
    }

    @Test(expected = Exception.class)
    public void shouldThrowSysExceptionWhenCustomersNull() {
        Slice<Customer> customerSlice = new SliceImpl<>(customerList);
        when(customerRepository.findBy(null)).thenReturn(customerSlice);

        customerService.getCustomersBySlice(pageable);
    }

    @Test
    public void shouldGetCustomerByPage() {
        Page<Customer> customerPage = new PageImpl<>(customerList);
        when(customerRepository.findAll(pageable)).thenReturn(customerPage);
        when(customerMapper.toDTO(customer)).thenReturn(customerDTO);

        Page<CustomerDTO> customerDTOPage = customerPage.map(customerMapper::toDTO);
        Page<CustomerDTO> result = customerService.getCustomersByPage(pageable);

        assertNotNull(result);
        assertEquals(result,customerDTOPage);
    }
}