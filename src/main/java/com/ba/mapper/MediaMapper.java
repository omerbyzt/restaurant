package com.ba.mapper;

import com.ba.dto.MediaDTO;
import com.ba.entity.Media;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface MediaMapper {
//    MediaMapper INSTANCE = Mappers.getMapper(MediaMapper.class);

    Media toEntity(MediaDTO mediaDTO);
    MediaDTO toDTO(Media media);
    List<MediaDTO> toDTOList(List<Media> mediaList);
    List<Media> toList(List<MediaDTO> mediaDTOList);
}
