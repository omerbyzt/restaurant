//package com.ba.converter;
//
//import com.ba.dto.MediaDTO;
//import com.ba.entity.Media;
//
//import java.util.ArrayList;
//import java.util.List;
//
//public class MediaConverter {
//    public static List<MediaDTO> convertListToListDTO(List<Media> mediaList){
//        List<MediaDTO> dtoList = new ArrayList<>();
//
//        for(Media mediaItem: mediaList){
//            MediaDTO mediaDTO = new MediaDTO();
//            mediaDTO.setFileContent(mediaItem.getFileContent());
//            mediaDTO.setId(mediaItem.getId());
//            mediaDTO.setName(mediaItem.getName());
//
//            dtoList.add(mediaDTO);
//        }
//
//        return dtoList;
//    }
//
//    public static Media convertMediaDTOToMedia(MediaDTO mediaDTO){
//        Media media = new Media();
//
//        if(mediaDTO.getId()!= null){
//            media.setId(mediaDTO.getId());
//        }
//        if(mediaDTO.getName()!= null){
//            media.setName(mediaDTO.getName());
//        }
//        if(mediaDTO.getFileContent() != null){
//            media.setFileContent(mediaDTO.getFileContent());
//        }
//
//        return media;
//    }
//
//    public static Media convertMediaDTOToMediaOneToOne(MediaDTO mediaDTO){
//        Media media = new Media();
//
//        //media.setId(mediaDTO.getId());
//        media.setName(mediaDTO.getName());
//        media.setFileContent(mediaDTO.getFileContent());
//        //media.setWaiter(media.getWaiter());
//
//        return media;
//    }
//
//    public static MediaDTO convertMediaToMediaDTO(Media media){
//        MediaDTO mediaDTO = new MediaDTO();
//
//        mediaDTO.setName(media.getName());
//        mediaDTO.setId(media.getId());
//        mediaDTO.setFileContent(media.getFileContent());
//
//        return mediaDTO;
//    }
//}
