package com.ba.mapper;

import com.ba.dto.MediaDTO;
import com.ba.entity.Media;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2020-12-31T11:11:04+0300",
    comments = "version: 1.3.1.Final, compiler: javac, environment: Java 11.0.9 (Oracle Corporation)"
)
@Component
public class MediaMapperImpl implements MediaMapper {

    @Override
    public Media toEntity(MediaDTO mediaDTO) {
        if ( mediaDTO == null ) {
            return null;
        }

        Media media = new Media();

        media.setId( mediaDTO.getId() );
        media.setDeleted( mediaDTO.isDeleted() );
        media.setName( mediaDTO.getName() );
        byte[] fileContent = mediaDTO.getFileContent();
        if ( fileContent != null ) {
            media.setFileContent( Arrays.copyOf( fileContent, fileContent.length ) );
        }

        return media;
    }

    @Override
    public MediaDTO toDTO(Media media) {
        if ( media == null ) {
            return null;
        }

        MediaDTO mediaDTO = new MediaDTO();

        mediaDTO.setId( media.getId() );
        mediaDTO.setName( media.getName() );
        byte[] fileContent = media.getFileContent();
        if ( fileContent != null ) {
            mediaDTO.setFileContent( Arrays.copyOf( fileContent, fileContent.length ) );
        }
        mediaDTO.setDeleted( media.isDeleted() );

        return mediaDTO;
    }

    @Override
    public List<MediaDTO> toDTOList(List<Media> mediaList) {
        if ( mediaList == null ) {
            return null;
        }

        List<MediaDTO> list = new ArrayList<MediaDTO>( mediaList.size() );
        for ( Media media : mediaList ) {
            list.add( toDTO( media ) );
        }

        return list;
    }

    @Override
    public List<Media> toList(List<MediaDTO> mediaDTOList) {
        if ( mediaDTOList == null ) {
            return null;
        }

        List<Media> list = new ArrayList<Media>( mediaDTOList.size() );
        for ( MediaDTO mediaDTO : mediaDTOList ) {
            list.add( toEntity( mediaDTO ) );
        }

        return list;
    }
}
