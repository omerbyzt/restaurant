package com.ba.dto;

import lombok.*;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TableCategoryDTO {

    private long id;

    @NotNull(message = "Table category name cannot null...!")
    private String name;

    @NotNull(message = "Table category number cannot null...!")
    private int number;

    @NotNull(message = "Table category media cannot null...!")
    private MediaDTO media;

    private boolean deleted;
}
