package com.ba.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.io.Serializable;

@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
@SQLDelete(sql =
        "UPDATE CUSTOMER " +
                "SET deleted = true " +
                "WHERE id = ?")
@Where(clause = "deleted = false")
public class Customer extends BaseEntity implements Serializable{

    private String name;
    private String surname;
    private String phoneNumber;
    private String address;

    @ManyToOne
    @JoinColumn(name = "media_id")//fetch=Eager
    private Media media;
}
