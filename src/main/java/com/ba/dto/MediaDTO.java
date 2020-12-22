package com.ba.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MediaDTO {

    private Long id;
    private String name;
    private byte[] fileContent;
    private boolean deleted;
    //private WaiterDTO waiterDTO;
//    private List<Category> categories;
//
//    public List<Category> getCategories() {
//        return categories;
//    }
//
//    public void setCategories(List<Category> categories) {
//        this.categories = categories;
//    }

//
//    public WaiterDTO getWaiterDTO() {
//        return waiterDTO;
//    }
//
//    public void setWaiterDTO(WaiterDTO waiterDTO) {
//        this.waiterDTO = waiterDTO;
//    }

//    public Long getId() {
//        return id;
//    }
//
//    public void setId(Long id) {
//        this.id = id;
//    }
//
//    public String getName() {
//        return name;
//    }
//
//    public void setName(String name) {
//        this.name = name;
//    }
//
//    public byte[] getFileContent() {
//        return fileContent;
//    }
//
//    public void setFileContent(byte[] fileContent) {
//        this.fileContent = fileContent;
//    }
}
