package com.ba.controller;

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