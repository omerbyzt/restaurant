package com.ba.dto;

import lombok.*;

import javax.validation.constraints.NotNull;
import java.sql.Timestamp;
import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OrderDTO {
    private Long orderID;

    @NotNull(message = "Order product id cannot null...!")
    private Long pId;

    @NotNull(message = "Order product cannot null...!")
    private String name;

    @NotNull(message = "Order product price cannot null...!")
    private Long price;

    @NotNull(message = "Order product amount cannot null...!")
    private Long amount;

    @NotNull(message = "Order table name cannot null...!")
    private String tableName;

    private Date orderDate = new Timestamp(System.currentTimeMillis());

    @NotNull(message = "Order waiter id cannot null...!")
    private Long waiterID;

    private boolean deleted;
}
