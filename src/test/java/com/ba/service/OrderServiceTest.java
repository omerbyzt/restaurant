package com.ba.service;

import com.ba.dto.OrderDTO;
import com.ba.entity.Orderr;
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

    private Orderr order = new Orderr();
    private OrderDTO orderDTO = new OrderDTO();
    private List<OrderDTO> listOrderDTO = new ArrayList<>();
    private List<Orderr> listOrderr = new ArrayList<>();

    @Before
    public void setUp() throws Exception {
        Date tempDate = new Date();
        order.setTableName("Salon 1");
        order.setpId(1L);
        order.setAmount(3L);
        order.setName("Mercimek");
        order.setOrderDate(tempDate);
        order.setOrderID(1L);
        order.setPrice(15L);
        order.setWaiterID(1L);

        orderDTO.setTableName("Salon 1DTO");
        orderDTO.setpId(1L);
        orderDTO.setAmount(3L);
        orderDTO.setName("MercimekDTO");
        orderDTO.setOrderDate(tempDate);
        orderDTO.setOrderID(1L);
        orderDTO.setPrice(15L);
        orderDTO.setWaiterID(1L);

        listOrderDTO.add(orderDTO);
        listOrderr.add(order);
    }

    @Test
    public void shouldAddOrder() {

        Mockito.when(orderRepository.saveAll(any())).thenReturn(listOrderr);

        String res = orderService.addOrder(listOrderDTO);

        assertNotNull(res);
        assertEquals(res,"Order Added");
    }

    @Test
    public void shouldListOrder() {
        //Mockito.when(orderRepository.findAll()).thenReturn(listOrderr);

        List<OrderDTO> orderDTOList = orderService.listAllOrders();
        assertNotNull(orderDTOList);
    }
}