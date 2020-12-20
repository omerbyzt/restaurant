package com.ba.builder;

import com.ba.dto.MediaDTO;
import com.ba.dto.WaiterDTO;

public class WaiterDTOBuilder extends Builder{

    private String name;
    private String phoneNumber;
    private String mail;
    private String address;
    private String urlToImage;
    private Long salary;
    private MediaDTO mediaDTO;

    public WaiterDTOBuilder id(Long id){
        this.setId(id);
        return this;
    }

    public WaiterDTOBuilder name(String name){
        this.name = name;
        return this;
    }

    public WaiterDTOBuilder phoneNumber(String phoneNumber){
        this.phoneNumber = phoneNumber;
        return this;
    }

    public WaiterDTOBuilder mail(String mail){
        this.mail = mail;
        return this;
    }

    public WaiterDTOBuilder address(String address){
        this.address = address;
        return this;
    }

    public WaiterDTOBuilder urlToImage(String urlToImage){
        this.urlToImage = urlToImage;
        return this;
    }

    public WaiterDTOBuilder salary(Long salary){
        this.salary = salary;
        return this;
    }

    public WaiterDTOBuilder mediaDTO(MediaDTO mediaDTO){
        this.mediaDTO = mediaDTO;
        return this;
    }

    @Override
    public WaiterDTO build() {
        WaiterDTO waiterDTO = new WaiterDTO();

        waiterDTO.setId(this.getId());
        waiterDTO.setName(this.name);
        waiterDTO.setPhoneNumber(this.phoneNumber);
        waiterDTO.setMail(this.mail);
        waiterDTO.setAddress(this.address);
        waiterDTO.setUrlToImage(this.urlToImage);
        waiterDTO.setSalary(this.salary);
        waiterDTO.setMediaDTO(this.mediaDTO);

        return waiterDTO;
    }
}
