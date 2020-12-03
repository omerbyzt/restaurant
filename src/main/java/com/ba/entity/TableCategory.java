package com.ba.entity;

import com.ba.unused.Tables;

import javax.persistence.*;
import java.util.Set;

@Entity
public class TableCategory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;
    private int number;

    @OneToMany(
            cascade = CascadeType.ALL
    )

    @JoinColumn(name ="table_category_id")
    private Set<Tables> tables;


    public TableCategory(String name,int number) {
        this.name = name;
        this.number = number;
    }


    public TableCategory() {

    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
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
