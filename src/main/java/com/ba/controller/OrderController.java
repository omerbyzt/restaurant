package com.ba.controller;

import com.ba.dto.OrderAndCreditDTO;
import com.ba.dto.OrderDTO;
import com.ba.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/order")
public class OrderController {

    @Autowired
    private OrderService orderService;


//    @GetMapping("/listall")
//    public List<OrderDTO> listAllOrders() {
//        return orderService.listAllOrders();
//    }

    @PostMapping("/add")
    public ResponseEntity<String> addOrderAndCredit(@RequestBody OrderAndCreditDTO orderAndCreditDTO) {
        orderService.addOrderAndCredit(orderAndCreditDTO);
        return new ResponseEntity<>("Order and credit card added", HttpStatus.OK);
    }
}
