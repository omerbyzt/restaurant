package com.ba.builder;

import com.ba.dto.OrderDTO;
import com.ba.entity.Orderr;

import java.sql.Timestamp;
import java.util.Date;

public class OrderDTOBuilder {

    private Long orderID;
    private Long pId;
    private String name;
    private Long price;
    private Long amount;
    private String tableName;
    private Date orderDate = new Timestamp(System.currentTimeMillis());
    private Long waiterID;

    public OrderDTOBuilder orderID(Long orderID){
        this.orderID = orderID;
        return this;
    }

    public OrderDTOBuilder pId(Long pId){
        this.pId = pId;
        return this;
    }

    public OrderDTOBuilder name(String name){
        this.name = name;
        return this;
    }

    public OrderDTOBuilder price(Long price){
        this.price = price;
        return this;
    }

    public OrderDTOBuilder amount(Long amount){
        this.amount = amount;
        return this;
    }

    public OrderDTOBuilder tableName(String tableName){
        this.tableName = tableName;
        return this;
    }

    public OrderDTOBuilder waiterID(Long waiterID){
        this.waiterID = waiterID;
        return this;
    }

    public OrderDTO build(){
        OrderDTO orderDTO = new OrderDTO();

        orderDTO.setOrderID(this.orderID);
        orderDTO.setpId(this.pId);
        orderDTO.setName(this.name);
        orderDTO.setPrice(this.price);
        orderDTO.setAmount(this.amount);
        orderDTO.setTableName(this.tableName);
        orderDTO.setWaiterID(this.waiterID);
        orderDTO.setOrderDate(this.orderDate);

        return orderDTO;
    }
}
