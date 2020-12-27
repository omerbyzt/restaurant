package com.ba.mapper;

import com.ba.dto.MediaDTO;
import com.ba.dto.WaiterDTO;
import com.ba.entity.Media;
import com.ba.entity.Waiter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.annotation.processing.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2020-12-27T12:30:30+0300",
    comments = "version: 1.3.1.Final, compiler: javac, environment: Java 11.0.9 (Oracle Corporation)"
)
public class WaiterMapperImpl implements WaiterMapper {

    @Override
    public Waiter toEntity(WaiterDTO waiterDTO) {
        if ( waiterDTO == null ) {
            return null;
        }

        Waiter waiter = new Waiter();

        waiter.setMedia( mediaDTOToMedia( waiterDTO.getMediaDTO() ) );
        waiter.setId( waiterDTO.getId() );
        waiter.setDeleted( waiterDTO.isDeleted() );
        waiter.setName( waiterDTO.getName() );
        waiter.setPhoneNumber( waiterDTO.getPhoneNumber() );
        waiter.setMail( waiterDTO.getMail() );
        waiter.setAddress( waiterDTO.getAddress() );
        waiter.setUrlToImage( waiterDTO.getUrlToImage() );
        waiter.setSalary( waiterDTO.getSalary() );

        return waiter;
    }

    @Override
    public WaiterDTO toDTO(Waiter waiter) {
        if ( waiter == null ) {
            return null;
        }

        WaiterDTO waiterDTO = new WaiterDTO();

        waiterDTO.setMediaDTO( mediaToMediaDTO( waiter.getMedia() ) );
        waiterDTO.setId( waiter.getId() );
        waiterDTO.setName( waiter.getName() );
        waiterDTO.setPhoneNumber( waiter.getPhoneNumber() );
        waiterDTO.setMail( waiter.getMail() );
        waiterDTO.setAddress( waiter.getAddress() );
        waiterDTO.setUrlToImage( waiter.getUrlToImage() );
        waiterDTO.setSalary( waiter.getSalary() );
        waiterDTO.setDeleted( waiter.isDeleted() );

        return waiterDTO;
    }

    @Override
    public List<Waiter> toList(List<WaiterDTO> waiterDTOList) {
        if ( waiterDTOList == null ) {
            return null;
        }

        List<Waiter> list = new ArrayList<Waiter>( waiterDTOList.size() );
        for ( WaiterDTO waiterDTO : waiterDTOList ) {
            list.add( toEntity( waiterDTO ) );
        }

        return list;
    }

    @Override
    public List<WaiterDTO> toDTOList(List<Waiter> waiterList) {
        if ( waiterList == null ) {
            return null;
        }

        List<WaiterDTO> list = new ArrayList<WaiterDTO>( waiterList.size() );
        for ( Waiter waiter : waiterList ) {
            list.add( toDTO( waiter ) );
        }

        return list;
    }

    protected Media mediaDTOToMedia(MediaDTO mediaDTO) {
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

    protected MediaDTO mediaToMediaDTO(Media media) {
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
}
