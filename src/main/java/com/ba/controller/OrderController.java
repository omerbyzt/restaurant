package com.ba.controller;

import com.ba.dto.OrderDTO;
import com.ba.entity.Orderr;
import com.ba.exception.BusinessRuleException;
import com.ba.service.OrderService;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/order")
public class OrderController {

    @Autowired
    OrderService orderService;

    @GetMapping("/listall")
    public List<OrderDTO> listAllOrders() {
        return orderService.listAllOrders();
    }

    @PostMapping("/add")
    public String addOrder(@RequestBody List<OrderDTO> orderDTO) {
        if(orderDTO == null){
            throw new BusinessRuleException("List cannot be empty");
        }
        orderService.addOrder(orderDTO);
        return "Order Added";
    }
}
