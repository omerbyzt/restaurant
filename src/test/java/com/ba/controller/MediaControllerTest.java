package com.ba.controller;

import com.ba.builder.MediaBuilder;
import com.ba.builder.MediaDTOBuilder;
import com.ba.dto.MediaDTO;
import com.ba.entity.Media;
import com.ba.service.MediaService;
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
}