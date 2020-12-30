package com.ba.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OrderAndCreditDTO {
    private List<OrderDTO> orderDTOList;
    private CreditCardDTO creditCardDTO;
    private Long customerId;
}
