package com.ba.controller;

import com.ba.dto.OrderDTO;
import com.ba.entity.Orderr;
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
    public void addOrder(@RequestBody List<OrderDTO> orderDTO) {
        orderService.addOrder(orderDTO);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteOrder(@PathVariable Long id) {
        orderService.deleteOrder(id);
    }
}
