package com.ba.entity;

import lombok.*;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@SQLDelete(sql =
        "UPDATE TableCategory " +
                "SET deleted = true " +
                "WHERE id = ?")
@Where(clause = "deleted = false")
@Entity
@Table(name = "TableCategory")
public class TableCategory extends BaseEntity{

    private String name;
    private int number;

    @ManyToOne
    @JoinColumn(name = "media_id")//fetch=Eager
    private Media media;
}
