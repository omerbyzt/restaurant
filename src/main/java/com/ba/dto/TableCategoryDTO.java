package com.ba.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TableCategoryDTO {

    private long id;
    private String name;
    private int number;
    private boolean deleted;
    private MediaDTO media;
}
