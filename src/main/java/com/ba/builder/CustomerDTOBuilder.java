package com.ba.builder;

import com.ba.dto.CustomerDTO;
import com.ba.dto.MediaDTO;

public class CustomerDTOBuilder extends Builder{

    private String name;
    private String surname;
    private String phoneNumber;
    private String address;
    private MediaDTO media;

    public CustomerDTOBuilder id(Long id){
        this.setId(id);
        return this;
    }

    public CustomerDTOBuilder name(String name){
        this.name = name;
        return this;
    }

    public CustomerDTOBuilder surname(String surname){
        this.surname = surname;
        return this;
    }

    public CustomerDTOBuilder phoneNumber(String phoneNumber){
        this.phoneNumber = phoneNumber;
        return this;
    }

    public CustomerDTOBuilder address(String address){
        this.address = address;
        return this;
    }

    public CustomerDTOBuilder media(MediaDTO media){
        this.media = media;
        return this;
    }

    @Override
    public CustomerDTO build() {
        CustomerDTO customerDTO = new CustomerDTO();

        customerDTO.setId(this.getId());
        customerDTO.setName(this.name);
        customerDTO.setSurname(this.surname);
        customerDTO.setPhoneNumber(this.phoneNumber);
        customerDTO.setAddress(this.address);
        customerDTO.setMedia(this.media);

        return customerDTO;
    }
}
