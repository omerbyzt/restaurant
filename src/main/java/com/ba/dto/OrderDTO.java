package com.ba.dto;

import java.sql.Timestamp;
import java.util.Date;

public class OrderDTO {
    private Long orderID;
    private Long pId;
    private String name;
    private Long price;
    private Long amount;
    private String tableName;
    private Date orderDate = new Timestamp(System.currentTimeMillis());
    private Long waiterID;

    public Long getWaiterID() {
        return waiterID;
    }

    public void setWaiterID(Long waiterID) {
        this.waiterID = waiterID;
    }

    public Long getOrderID() {
        return orderID;
    }

    public void setOrderID(Long orderID) {
        this.orderID = orderID;
    }

    public Long getpId() {
        return pId;
    }

    public void setpId(Long pId) {
        this.pId = pId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getPrice() {
        return price;
    }

    public void setPrice(Long price) {
        this.price = price;
    }

    public Long getAmount() {
        return amount;
    }

    public void setAmount(Long amount) {
        this.amount = amount;
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }
}
