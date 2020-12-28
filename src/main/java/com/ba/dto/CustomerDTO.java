package com.ba.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CustomerDTO {
    //basedto
    private long id;
    private String name;
    private String surname;
    private String phoneNumber;
    private String address;
    private MediaDTO media;
}
