package com.ba.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@SQLDelete(sql =
        "UPDATE PaymentType " +
                "SET deleted = true " +
                "WHERE id = ?")
@Where(clause = "deleted = false")
@Table(name= "PaymentType")
public class PaymentType extends BaseEntity{
    private String type;
}
