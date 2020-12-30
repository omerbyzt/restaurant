//package com.ba.controller;
//
//import com.ba.dto.OrderAndCreditDTO;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.List;
//
//@RestController
//@CrossOrigin(origins = "*")
//@RequestMapping("/order")
//public class OrderController {
//
//    @Autowired
//    OrderService orderService;
//
//    @GetMapping("/listall")
//    public List<OrderDTO> listAllOrders() {
//        return orderService.listAllOrders();
//    }
//
//    @PostMapping("/add")
//    public String addOrder(@RequestBody OrderAndCreditDTO orderAndCreditDTO) {
//        orderService.addOrder(orderAndCreditDTO);
//        return "Order Added";
//    }
//}
