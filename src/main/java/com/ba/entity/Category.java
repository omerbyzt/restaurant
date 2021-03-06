package com.ba.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@SQLDelete(sql =
        "UPDATE Category " +
                "SET deleted = true " +
                "WHERE id = ?")
@Where(clause = "deleted = false")
@Entity
@Table(name = "Category")
public class Category extends BaseEntity{

    private String name;
    private String description;

    @JsonManagedReference
    @ManyToMany
    @JoinTable(name = "CATEGORY_PRODUCT", joinColumns = @JoinColumn(name = "category_id"), inverseJoinColumns = @JoinColumn(name = "product_id"))
    private List<Product> products;

    @JsonIgnore
    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(
            name = "media_id"
    )
    private Media media;

}
