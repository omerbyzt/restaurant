package com.ba.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OrderWrapper {
    private Long id;
    private Long productId;
    private String name;
    private String price;
    private int amount;
    private int totalPrice;
    private String tableName;
    private Long waiterID;
    private Long customerId;
}


