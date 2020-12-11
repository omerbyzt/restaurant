package com.ba.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.util.List;

@Entity
public class Media {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    @Column(length = 1000000)
    private byte[] fileContent;

//    @JsonBackReference
//    @OneToOne(mappedBy = "media")
//    private Waiter waiter;
//
//    public Waiter getWaiter() {
//        return waiter;
//    }
//
//    public void setWaiter(Waiter waiter) {
//        this.waiter = waiter;
//    }

//    @JsonIgnore
//    @OneToMany(
//            mappedBy = "category",//ilişkiyi kuracağımız değişkenin adı verilecek!! (categories olacaktı!)
//            cascade = CascadeType.ALL
//    )
//    private List<Category> categories;

//    public List<Category> getCategories() {
//        return categories;
//    }
//
//    public void setCategories(List<Category> categories) {
//        this.categories = categories;
//    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public byte[] getFileContent() {
        return fileContent;
    }

    public void setFileContent(byte[] fileContent) {
        this.fileContent = fileContent;
    }
}
