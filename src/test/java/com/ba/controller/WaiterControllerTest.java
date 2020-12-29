package com.ba.controller;

import com.ba.builder.WaiterBuilder;
import com.ba.builder.WaiterDTOBuilder;
import com.ba.dto.WaiterDTO;
import com.ba.entity.Waiter;
import com.ba.exception.BusinessRuleException;
import com.ba.service.WaiterService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.awt.image.BufferStrategy;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class WaiterControllerTest {

    @InjectMocks
    private WaiterController controller;

    @Mock
    private WaiterService service;

    private Waiter waiter = new Waiter();
    private WaiterDTO waiterDTO = new WaiterDTO();
    private List<Waiter> waiterList = new ArrayList<>();
    private List<WaiterDTO> waiterListDTO = new ArrayList<>();
    private Long id = 111L;

    @Before
    public void setUp() throws Exception {

        waiter = new WaiterBuilder().urlToImage("img").salary(1000L).phoneNumber("112").name("Omer").mail("mail").id(1L).address("address").build();
        waiterDTO = new WaiterDTOBuilder().urlToImage("imgDTO").salary(1000L).phoneNumber("112").name("OmerDTO").mail("mailDTO").id(1L).address("addressDTO").build();

        waiterList.add(waiter);
        waiterListDTO.add(waiterDTO);
    }

    @Test
    public void shouldVerifyListAllWaiters() {
        when(service.listAllWaiters()).thenReturn(waiterListDTO);
        List<WaiterDTO> tempDTOList = controller.listAllWaiters();
        assertEquals(tempDTOList,waiterListDTO);
    }

    @Test
    public void shouldVerifyAddWaiter() {
        when(service.addWaiter(waiterDTO)).thenReturn("Waiter Added");
        String res = controller.addWaiter(waiterDTO);
        assertEquals(res,"Waiter Added");
    }

    @Test
    public void shouldVerifyDeleteWaiter() {
        when(service.deleteWaiter(id)).thenReturn("Waiter Deleted");
        String res = controller.deleteWaiter(id);
        assertEquals(res,"Waiter Deleted");
    }

    @Test(expected = BusinessRuleException.class)
    public void shouldNotDeleteWaiter() {
        when(service.deleteWaiter(id)).thenReturn("Waiter Deleted");
        String res = controller.deleteWaiter(null);
    }

    @Test
    public void shouldVerifyUpdateWaiter() {
        when(service.updateWaiter(waiterDTO)).thenReturn(waiterDTO);
        WaiterDTO tempDTO = controller.updateWaiter(waiterDTO);
        assertEquals(tempDTO,waiterDTO);
    }

    @Test(expected = BusinessRuleException.class)
    public void shouldNotUpdateWaiter() {
        waiterDTO.setId(null);
        when(service.updateWaiter(waiterDTO)).thenReturn(waiterDTO);
        controller.updateWaiter(waiterDTO);
    }
}