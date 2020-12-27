package com.ba.controller;

import com.ba.builder.MediaBuilder;
import com.ba.builder.MediaDTOBuilder;
import com.ba.dto.MediaDTO;
import com.ba.entity.Media;
import com.ba.exception.BusinessRuleException;
import com.ba.exception.SystemException;
import com.ba.service.MediaService;
import com.sun.xml.bind.v2.runtime.unmarshaller.XmlVisitor;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class MediaControllerTest {

    @InjectMocks
    private MediaController controller;

    @Mock
    private MediaService service;

    private Media media = new Media();
    private MediaDTO mediaDTO = new MediaDTO();
    private List<MediaDTO> mediaDTOList = new ArrayList<>();
    private MultipartFile multipartFile = new MultipartFile() {
        @Override
        public String getName() {
            return null;
        }

        @Override
        public String getOriginalFilename() {
            return null;
        }

        @Override
        public String getContentType() {
            return null;
        }

        @Override
        public boolean isEmpty() {
            return false;
        }

        @Override
        public long getSize() {
            return 0;
        }

        @Override
        public byte[] getBytes() throws IOException {
            return new byte[0];
        }

        @Override
        public InputStream getInputStream() throws IOException {
            return null;
        }

        @Override
        public void transferTo(File file) throws IOException, IllegalStateException {

        }
    };
    private Long id = 111L;

    @Before
    public void setUp() throws Exception {
        media = new MediaBuilder().id(1L).name("imageName").fileContent(null).build();
        mediaDTO = new MediaDTOBuilder().id(1L).name("imageNameDTO").fileContent(null).build();

        mediaDTOList.add(mediaDTO);

    }

    @Test
    public void shouldVerifyListWholeMedia() {
        when(service.getWholeFiles()).thenReturn(mediaDTOList);
        List<MediaDTO> dtoList = controller.getWholeFiles();

        assertEquals(mediaDTOList,dtoList);

    }

    @Test
    public void shouldVerifyAddNewMedia() throws IOException {
        when(service.addFile(multipartFile,"deneme")).thenReturn("File Added");
        String res = controller.addFile(multipartFile,"deneme");

        assertEquals(res,"File Added");
    }

    @Test(expected = SystemException.class)
    public void shouldNotAddMedia() throws IOException {
        when(controller.addFile(null,null)).thenReturn("File Added");
        String res = controller.addFile(null,null);
    }

    @Test
    public void shouldVerifyGetMediaByID() {
        when(service.getMediaByID(id)).thenReturn(mediaDTO);
        MediaDTO tempDTO = controller.getMediaByID(id);

        assertEquals(tempDTO,mediaDTO);
    }

    @Test(expected = BusinessRuleException.class)
    public void shouldNotGetMediaByID() {
        when(service.getMediaByID(id)).thenReturn(mediaDTO);
        MediaDTO tempDTO = controller.getMediaByID(null);
    }

    @Test
    public void shouldDeleteMedia() {
        when(service.deleteMedia(id)).thenReturn("Media Deleted!");
        String res = controller.deleteMedia(id);

        assertNotNull(res);
    }

    @Test(expected = BusinessRuleException.class)
    public void shouldNotDeleteMedia() {
        when(service.deleteMedia(id)).thenReturn("Media Deleted!");
        String res = controller.deleteMedia(null);
    }
}