package com.ba.builder;

import com.ba.entity.Media;

public class MediaBuilder extends Builder{
    private String name;
    private byte[] fileContent;

    public MediaBuilder id(Long id){
        this.setId(id);
        return this;
    }

    public MediaBuilder name(String name){
        this.name = name;
        return this;
    }

    public MediaBuilder fileContent(byte[] fileContent){
        this.fileContent = fileContent;
        return this;
    }

    @Override
    public Media build() {
        Media media = new Media();
        media.setName(this.name);
        media.setId(this.getId());
        media.setFileContent(this.fileContent);
        return media;
    }
}
