package com.ba.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@SQLDelete(sql =
        "UPDATE WAITER " +
                "SET deleted = true " +
                "WHERE id = ?")
@Where(clause = "deleted = false")
@Entity
public class Waiter extends BaseEntity{

//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long id;
    private String name;
    private String phoneNumber;
    private String mail;
    private String address;
    private String urlToImage;
    private Long salary;

    @JsonManagedReference
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "media_id")
    private Media media;
}
