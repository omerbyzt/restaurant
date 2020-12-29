package com.ba.service;

import com.ba.dto.OrderDTO;
import com.ba.entity.Orderr;
import com.ba.exception.SystemException;
import com.ba.mapper.MediaMapper;
import com.ba.mapper.OrderMapper;
import com.ba.repository.OrderRepository;
import liquibase.pro.packaged.O;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Locale;
import java.util.Optional;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private OrderMapper orderMapper;

    public List<OrderDTO> listAllOrders(){
        List<Orderr> orderList = orderRepository.findAll();
        return orderMapper.toDTOList(orderList);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public String addOrder(List<OrderDTO> orderDTO){
        orderRepository.saveAll(orderMapper.toList(orderDTO));
        return "Order Added";
    }
}
