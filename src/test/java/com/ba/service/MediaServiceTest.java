package com.ba.service;

import com.ba.builder.MediaBuilder;
import com.ba.builder.MediaDTOBuilder;
import com.ba.dto.MediaDTO;
import com.ba.entity.Media;
import com.ba.mapper.MediaMapper;
import com.ba.repository.MediaRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.mock.web.MockMultipartFile;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class MediaServiceTest {
    @InjectMocks
    private MediaService service;

    @Mock
    private MediaRepository repository;

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

        List<MediaDTO> tempDTOList = MediaMapper.INSTANCE.toDTOList(mediaList);
//        List<MediaDTO> tempDTOList = MediaConverter.convertListToListDTO(mediaList);
        List<MediaDTO> tempDTOList2 = service.getWholeFiles();

        assertEquals(tempDTOList.get(0).getId(),tempDTOList2.get(0).getId());
    }

    @Test
    public void shouldAddNewMedia() throws IOException {
        when(repository.save(media)).thenReturn(media);
        String res = service.addFile(file,"zar.png");

        assertNotNull(res);
        assertEquals(res,"File Added");
    }

    @Test
    public void shoulVerifyGetMediaByID() {
//        when(service.getMediaByID(id)).thenReturn(mediaDTO);
//        MediaDTO mediaDTO = service.getMediaByID(id);
//
//        assertNotNull(mediaDTO);

    }
}