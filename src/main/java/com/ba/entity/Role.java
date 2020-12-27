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
        "UPDATE ROLES " +
                "SET deleted = true " +
                "WHERE id = ?")
@Where(clause = "deleted = false")
@Entity
@Table(name = "ROLES")
public class Role extends BaseEntity{

    private String name;
}
