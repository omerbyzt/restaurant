package com.ba.controller;

import com.ba.builder.OrderBuilder;
import com.ba.builder.OrderDTOBuilder;
import com.ba.dto.OrderDTO;
import com.ba.entity.Orderr;
import com.ba.service.OrderService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class OrderControllerTest {

    @InjectMocks
    private OrderController controller;

    @Mock
    private OrderService service;

    private Orderr order = new Orderr();
    private OrderDTO orderDTO = new OrderDTO();
    private List<Orderr> orderList = new ArrayList<>();
    private List<OrderDTO> orderListDTO = new ArrayList<>();

    @Before
    public void setUp() throws Exception {

        order = new OrderBuilder().tableName("Salon 1").pId(1L).amount(3L).name("Mercimek").orderID(1L).price(15L).waiterID(1L).build();
        orderDTO = new OrderDTOBuilder().tableName("Salon 1DTO").pId(1L).amount(3L).name("MercimekDTO").orderID(1L).price(15L).waiterID(1L).build();

        orderListDTO.add(orderDTO);
        orderList.add(order);
    }

    @Test
    public void shouldVerifyListAllOrders() {

        when(service.listAllOrders()).thenReturn(orderListDTO);

        List<OrderDTO> tempOrderListDTO = controller.listAllOrders();

        assertEquals(tempOrderListDTO,orderListDTO);
    }

    @Test
    public void shouldVerifyAddOrder() {
        when(service.addOrder(orderListDTO)).thenReturn("Order Added");
        String res = controller.addOrder(orderListDTO);

        assertEquals(res,"Order Added");
    }
}