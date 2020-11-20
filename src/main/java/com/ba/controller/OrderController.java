package com.ba.controller;

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
    public List<Orderr> listAllOrders(){
        return orderService.listAllOrders();
    }

    @PostMapping("/add")
    public List<Orderr>addOrder(@RequestBody List<Orderr> order){
        return orderService.addOrder(order);
    }

    @DeleteMapping("/delete/{id}")
    public List<Orderr> deleteOrder(@PathVariable Long id){
        return orderService.deleteOrder(id);
    }
}
