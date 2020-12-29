package com.ba.dto;

import lombok.*;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class WaiterDTO {

    private Long id;

    @NotNull(message = "Waiter name cannot null...!")
    private String name;

    @NotNull(message = "Waiter phone number cannot null...!")
    private String phoneNumber;

    @NotNull(message = "Waiter mail cannot null...!")
    private String mail;

    @NotNull(message = "Waiter address cannot null...!")
    private String address;

    private String urlToImage;

    @NotNull(message = "Waiter salary cannot null...!")
    private Long salary;

    @NotNull(message = "Waiter media cannot null...!")
    private MediaDTO mediaDTO;

    private boolean deleted;
}
