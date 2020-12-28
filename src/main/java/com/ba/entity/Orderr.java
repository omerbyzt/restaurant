package com.ba.entity;

import lombok.*;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.util.Date;
import java.sql.Timestamp;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@SQLDelete(sql =
        "UPDATE Orderr " +
                "SET deleted = true " +
                "WHERE orderID = ?")
@Where(clause = "deleted = false")
@Entity
@Table(name = "Orderr")
public class Orderr {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long orderID;
    private Long pId;
    private String name;
    private Long price;
    private Long amount;
    private String tableName;
    private Long waiterID;
    @Column
    private Date orderDate = new Timestamp(System.currentTimeMillis());

    private boolean deleted;
}
