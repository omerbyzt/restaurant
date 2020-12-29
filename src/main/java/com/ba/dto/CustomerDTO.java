package com.ba.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CustomerDTO {
    //basedto
    private long id;

    @NotNull(message = "Customer name cannot null...!")
    private String name;

    @NotNull(message = "Customer surname cannot null...!")
    private String surname;

    @NotNull(message = "Customer phone number cannot null...!")
    private String phoneNumber;

    @NotNull(message = "Customer address cannot null...!")
    private String address;

    @NotNull(message = "Customer media cannot null...!")
    private MediaDTO media;
}
