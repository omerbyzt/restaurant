package com.ba.builder;

import com.ba.dto.MediaDTO;

public class MediaDTOBuilder extends Builder{
    private String name;
    private byte[] fileContent;

    public MediaDTOBuilder id(Long id){
        this.setId(id);
        return this;
    }

    public MediaDTOBuilder name(String name){
        this.name = name;
        return this;
    }

    public MediaDTOBuilder fileContent(byte[] fileContent){
        this.fileContent = fileContent;
        return this;
    }

    @Override
    public MediaDTO build(){
        MediaDTO mediaDTO = new MediaDTO();
        mediaDTO.setName(this.name);
        mediaDTO.setId(this.getId());
        mediaDTO.setFileContent(this.fileContent);
        return mediaDTO;
    }
}
