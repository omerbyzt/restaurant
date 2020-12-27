package com.ba.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;
import org.springframework.data.repository.cdi.Eager;

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
                "WHERE id = ?")
@Where(clause = "deleted = false")
@Entity
public class Product extends BaseEntity{

    private String productName;
    private String productDesc;
    private String productCategory;
    private Double productPrice;

    @JsonBackReference
    @ManyToMany(
            mappedBy = "products",
            fetch = FetchType.EAGER
//            cascade = CascadeType.DETACH
    )
    private List<Category> categories = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "media_id")//fetch=Eager
    private Media media;
}
