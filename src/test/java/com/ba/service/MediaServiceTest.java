package com.ba.service;

import com.ba.builder.MediaBuilder;
import com.ba.builder.MediaDTOBuilder;
import com.ba.dto.MediaDTO;
import com.ba.entity.Media;
import com.ba.exception.SystemException;
import com.ba.helper.MediaHelper;
import com.ba.mapper.MediaMapper;
import com.ba.repository.MediaRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.mock.web.MockMultipartFile;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class MediaServiceTest {
    @InjectMocks
    private MediaService service;

    @Mock
    private MediaRepository repository;

    @Mock
    private MediaMapper mediaMapper;

    private Media media = new Media();
    private MediaDTO mediaDTO = new MediaDTO();
    private List<Media> mediaList = new ArrayList<>();
    private List<MediaDTO> mediaDTOList = new ArrayList<>();
    private Long id = 111L;

    byte [] b ={ (byte)0xe0, 0x4f, (byte)0xd0,
            0x20, (byte)0xea, 0x3a, 0x69, 0x10, (byte)0xa2, (byte)0xd8, 0x08, 0x00, 0x2b,
            0x30, 0x30, (byte)0x9d };

    byte[] json = "{\"name\":\"test\"}".getBytes(StandardCharsets.UTF_8);
    MockMultipartFile file = new MockMultipartFile("json", "json", "application/json", json);

    @Before
    public void setUp() throws Exception {
        media = new MediaBuilder().id(1L).name("zar.png").fileContent(b).build();
        mediaDTO = new MediaDTOBuilder().id(1L).name("zar.png").fileContent(b).build();

        mediaList.add(media);
        mediaDTOList.add(mediaDTO);
    }

    @Test
    public void shouldListMedias() {
        when(repository.findAll()).thenReturn(mediaList);
        when(mediaMapper.toDTOList(mediaList)).thenReturn(mediaDTOList);

        List<MediaDTO> result = service.getWholeFiles();

        assertNotNull(result);
        assertEquals(result,mediaDTOList);
    }

    @Test(expected = SystemException.class)
    public void shouldThrowSysExceptionWhenMediaListEmpty() {
        service.getWholeFiles();
    }

    @Test(expected = SystemException.class)
    public void shouldThrowSysExceptionWhenMediaNotFound() {
        service.getWholeFiles();
    }

    @Test
    public void shouldAddNewMedia() throws IOException {
        when(repository.save(media)).thenReturn(media);
        String res = service.addFile(file,"zar.png");

        assertNotNull(res);
        assertEquals(res,"File Added");
    }

//    @Test(expected = SystemException.class)
//    public void shouldThrowSysExceptionWhenMediaNull() throws IOException {
//        media.setId(null);
//        when(MediaHelper.addHelper(file,"testString")).thenReturn(media);
//        service.addFile(file,"testString");
//    }

    @Test
    public void shouldGetMediaByID() {
        when(repository.findById(id)).thenReturn(Optional.of(media));
        when(mediaMapper.toDTO(media)).thenReturn(mediaDTO);

        MediaDTO result = service.getMediaByID(id);
        assertNotNull(result);
        assertEquals(result,mediaDTO);
    }

    @Test(expected = SystemException.class)
    public void shouldThrowSysExceptionWhenMediaIdNull() {
        service.getMediaByID(id);
    }

    @Test
    public void shouldDeleteMedia() {
        String res = service.deleteMedia(id);
        verify(repository).deleteById(id);
        assertNotNull(res);
    }
}