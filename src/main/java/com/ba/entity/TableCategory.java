package com.ba.entity;

import javax.persistence.*;
import java.util.Set;

@Entity
public class TableCategory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;

    @OneToMany(
            cascade = CascadeType.ALL
    )

    @JoinColumn(name ="table_category_id")
    private Set<Tables> tables;


    public TableCategory(String name) {
        this.name = name;
    }


    public TableCategory() {

    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Tables> getTables() {
        return tables;
    }

    public void setTables(Set<Tables> tables) {
        this.tables = tables;
    }
}
