package com.ba.service;

import com.ba.converter.OrderConverter;
import com.ba.dto.OrderDTO;
import com.ba.entity.Orderr;
import com.ba.entity.Product;
import com.ba.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class OrderService {

    @Autowired
    OrderRepository orderRepository;

    public List<OrderDTO> listAllOrders(){
        return OrderConverter.convertOrderListToOrderListDTO(orderRepository.findAll());
    }

    public void addOrder(List<OrderDTO> orderDTO){
        orderRepository.saveAll(OrderConverter.convertOrderListDTOToOrderList(orderDTO));
    }

    public void deleteOrder(Long id){
        orderRepository.deleteById(id);
    }
}
