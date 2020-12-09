package com.ba.builder;

import com.ba.entity.Orderr;

import java.sql.Timestamp;
import java.util.Date;

public class OrderBuilder {
    private Long orderID;
    private Long pId;
    private String name;
    private Long price;
    private Long amount;
    private String tableName;
    private Date orderDate = new Timestamp(System.currentTimeMillis());
    private Long waiterID;

    public OrderBuilder orderID(Long orderID){
        this.orderID = orderID;
        return this;
    }

    public OrderBuilder pId(Long pId){
        this.pId = pId;
        return this;
    }

    public OrderBuilder name(String name){
        this.name = name;
        return this;
    }

    public OrderBuilder price(Long price){
        this.price = price;
        return this;
    }

    public OrderBuilder amount(Long amount){
        this.amount = amount;
        return this;
    }

    public OrderBuilder tableName(String tableName){
        this.tableName = tableName;
        return this;
    }

    public OrderBuilder waiterID(Long waiterID){
        this.waiterID = waiterID;
        return this;
    }

    public Orderr build(){
        Orderr order = new Orderr();

        order.setOrderID(this.orderID);
        order.setpId(this.pId);
        order.setName(this.name);
        order.setPrice(this.price);
        order.setAmount(this.amount);
        order.setTableName(this.tableName);
        order.setWaiterID(this.waiterID);
        order.setOrderDate(this.orderDate);

        return order;
    }


}
