package com.ba.controller;

import com.ba.dto.WaiterDTO;
import com.ba.entity.Waiter;
import com.ba.service.WaiterService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

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
        waiter.setUrlToImage("img");
        waiter.setSalary(1000L);
        waiter.setPhoneNumber("112");
        waiter.setName("Omer");
        waiter.setMail("mail");
        waiter.setId(1L);
        waiter.setAddress("address");

        waiterDTO.setUrlToImage("imgDTO");
        waiterDTO.setSalary(1000L);
        waiterDTO.setPhoneNumber("112");
        waiterDTO.setName("OmerDTO");
        waiterDTO.setMail("mailDTO");
        waiterDTO.setId(1L);
        waiterDTO.setAddress("addressDTO");

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

    @Test
    public void shouldVerifyUpdateWaiter() {
        when(service.updateWaiter(waiterDTO)).thenReturn(waiterDTO);
        WaiterDTO tempDTO = controller.updateWaiter(waiterDTO);
        assertEquals(tempDTO,waiterDTO);
    }
}