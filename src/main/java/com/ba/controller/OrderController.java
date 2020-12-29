package com.ba.controller;

import com.ba.dto.OrderDTO;
import com.ba.entity.Orderr;
import com.ba.exception.BusinessRuleException;
import com.ba.helper.InternationalizationHelper;
import com.ba.service.OrderService;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;
import java.util.Locale;

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
    public String addOrder(@Valid @RequestBody List<OrderDTO> orderDTO) {
        orderService.addOrder(orderDTO);
        return "Order Added";
    }
}
