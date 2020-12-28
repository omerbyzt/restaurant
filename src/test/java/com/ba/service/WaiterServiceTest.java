package com.ba.service;

import com.ba.builder.MediaBuilder;
import com.ba.builder.MediaDTOBuilder;
import com.ba.builder.WaiterBuilder;
import com.ba.builder.WaiterDTOBuilder;
import com.ba.dto.MediaDTO;
import com.ba.dto.WaiterDTO;
import com.ba.entity.Media;
import com.ba.entity.Waiter;
import com.ba.mapper.WaiterMapper;
import com.ba.repository.MediaRepository;
import com.ba.repository.WaiterRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.doThrow;
import static org.mockito.internal.verification.VerificationModeFactory.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class WaiterServiceTest {

    @InjectMocks
    private WaiterService service;

    @Mock
    private WaiterRepository repository;

    @Mock
    private MediaRepository mediaRepository;

    @Mock
    private WaiterMapper waiterMapper;

    private Waiter waiter = new Waiter();
    private WaiterDTO waiterDTO = new WaiterDTO();
    private List<Waiter> waiterList = new ArrayList<>();
    private List<WaiterDTO> waiterListDTO = new ArrayList<>();
    private Long id = 111L;
    private Media media = new Media();
    private MediaDTO mediaDTO = new MediaDTO();

    @Before
    public void setUp() throws Exception {
        media = new MediaBuilder().id(id).build();
        mediaDTO = new MediaDTOBuilder().id(id).build();

        waiter = new WaiterBuilder().urlToImage("img").salary(1000L).phoneNumber("112").name("Omer").mail("mail").id(1L).address("address").media(media).build();
        waiterDTO = new WaiterDTOBuilder().urlToImage("imgDTO").salary(1000L).phoneNumber("112").name("OmerDTO").mail("mailDTO").id(1L).address("addressDTO").mediaDTO(mediaDTO).build();

        waiterList.add(waiter);
        waiterListDTO.add(waiterDTO);
    }

    @Test
    public void shouldAddNewWaiter() {

//        when(repository.save(waiter)).thenReturn(waiter);
//        String res = service.addWaiter(waiterDTO);
//
//        assertNotNull(res);
//        assertEquals(res,"Waiter Added");
    }

    @Test
    public void shouldDeleteWaiter() {
        String res = service.deleteWaiter(id);
        assertEquals(res,"Waiter Deleted");
        verify(repository,times(1)).deleteById(id);
    }

    @Test(expected = RuntimeException.class)
    public void shouldNotDeleteWaiterWhenThrownException() {
        doThrow(new RuntimeException("Cant delete here")).when(repository).deleteById(id);
        String res = service.deleteWaiter(id);
    }

    @Test
    public void shouldUpdateWaiter() {
        when(repository.saveAndFlush(any())).thenReturn(waiter);
        WaiterDTO tempDTO = service.updateWaiter(waiterDTO);
        assertNotNull(tempDTO);
        assertEquals(tempDTO,waiterDTO);
    }

    @Test
    public void shouldListWaiters() {
        when(repository.findAll()).thenReturn(waiterList);

        List<WaiterDTO> tempDTOList = waiterMapper.toDTOList(waiterList);
//        List<WaiterDTO> tempDTOList = WaiterConverter.convertListToDTOList(waiterList);
        List<WaiterDTO> tempDTOList2 = service.listAllWaiters();

        assertEquals(tempDTOList.get(0).getId(), tempDTOList2.get(0).getId());
    }
}