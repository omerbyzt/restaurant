package com.ba.service;

import com.ba.dto.OrderDTO;
import com.ba.entity.Orderr;
import com.ba.exception.SystemException;
import com.ba.mapper.OrderMapper;
import com.ba.repository.OrderRepository;
import liquibase.pro.packaged.O;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    public List<OrderDTO> listAllOrders(){
        List<Orderr> orderList = orderRepository.findAll();
        if(orderList.isEmpty()){
            throw new SystemException("Orders not found...!");
        }
        return OrderMapper.INSTANCE.toDTOList(orderList);
    }

    public String addOrder(List<OrderDTO> orderDTO){
        orderRepository.saveAll(OrderMapper.INSTANCE.toList(orderDTO));
        return "Order Added";
    }
}
