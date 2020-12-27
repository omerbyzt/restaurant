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
        "UPDATE MEDIA " +
                "SET deleted = true " +
                "WHERE id = ?")
@Where(clause = "deleted = false")
@Entity
public class Media extends BaseEntity{

    private String name;

    @Column(length = 1000000)
    private byte[] fileContent;

}
