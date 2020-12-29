package com.ba.service;

import com.ba.builder.OrderBuilder;
import com.ba.builder.OrderDTOBuilder;
import com.ba.dto.OrderDTO;
import com.ba.entity.Orderr;
import com.ba.mapper.OrderMapper;
import com.ba.repository.OrderRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.mockito.Matchers.any;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(MockitoJUnitRunner.class)
public class OrderServiceTest {

    @InjectMocks
    private OrderService orderService;

    @Mock
    private OrderRepository orderRepository;

    @Mock
    private OrderMapper orderMapper;

    private Orderr order = new Orderr();
    private OrderDTO orderDTO = new OrderDTO();
    private List<OrderDTO> listOrderDTO = new ArrayList<>();
    private List<Orderr> listOrderr = new ArrayList<>();

    @Before
    public void setUp() throws Exception {

        order = new OrderBuilder().tableName("Salon 1").pId(1L).amount(3L).name("Mercimek").orderID(1L).price(15L).waiterID(1L).build();
        orderDTO = new OrderDTOBuilder().tableName("Salon 1DTO").pId(1L).amount(3L).name("MercimekDTO").orderID(1L).price(15L).waiterID(1L).build();

        listOrderDTO.add(orderDTO);
        listOrderr.add(order);
    }

    @Test
    public void shouldAddOrder() {
        Mockito.when(orderRepository.saveAll(any())).thenReturn(listOrderr);
        Mockito.when(orderMapper.toDTOList(listOrderr)).thenReturn(listOrderDTO);
        String res = orderService.addOrder(listOrderDTO);

        assertNotNull(res);
        assertEquals(res,"Order Added");
    }

    @Test
    public void shouldListOrder() {
        Mockito.when(orderRepository.findAll()).thenReturn(listOrderr);
        Mockito.when(orderMapper.toDTOList(listOrderr)).thenReturn(listOrderDTO);

        List<OrderDTO> result = orderService.listAllOrders();
        assertNotNull(result);
        assertEquals(result,listOrderDTO);
    }
}