package com.ba.dto;

import com.ba.entity.PaymentType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OrderDTO {
    private Long id;
    private PaymentTypeDTO paymentType;
    private CustomerDTO customer;
    private WaiterDTO waiter;
    private OrderItemDTO orderItem;
    private Long totalAmount;
    private Long totalCount;
    private Date orderDate;
}
