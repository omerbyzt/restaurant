package com.ba.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@SQLDelete(sql =
        "UPDATE PRODUCT " +
                "SET deleted = true " +
                "WHERE productID = ?")
@Where(clause = "deleted = false")
@Entity
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long productID;
    private String productName;
    private String productDesc;
    private String productCategory;
    private Double productPrice;

    @JsonBackReference
    @ManyToMany(mappedBy = "products", cascade = CascadeType.DETACH)
    private List<Category> categories = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "media_id")
    private Media media;

    private boolean deleted;

}
