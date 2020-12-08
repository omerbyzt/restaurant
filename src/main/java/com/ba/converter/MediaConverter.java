package com.ba.converter;

import com.ba.dto.MediaDTO;
import com.ba.entity.Media;

import java.util.ArrayList;
import java.util.List;

public class MediaConverter {
    public static List<MediaDTO> convertListToListDTO(List<Media> mediaList){
        List<MediaDTO> dtoList = new ArrayList<>();

        for(Media mediaItem: mediaList){
            MediaDTO mediaDTO = new MediaDTO();
            mediaDTO.setFileContent(mediaItem.getFileContent());
            mediaDTO.setId(mediaItem.getId());
            mediaDTO.setName(mediaItem.getName());

            dtoList.add(mediaDTO);
        }

        return dtoList;
    }
}
