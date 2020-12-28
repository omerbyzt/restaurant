package com.ba.dto;

import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MediaDTO implements Serializable {

    private Long id;
    private String name;
    private byte[] fileContent;
    private boolean deleted;
}
