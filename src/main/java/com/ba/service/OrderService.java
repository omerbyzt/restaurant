package com.ba.service;

import com.ba.entity.Orderr;
import com.ba.entity.Product;
import com.ba.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {

    @Autowired
    OrderRepository orderRepository;

    public List<Orderr> listAllOrders(){
        return orderRepository.findAll();
    }

    public List<Orderr> addOrder(List<Orderr> order){
        orderRepository.saveAll(order);
        return listAllOrders();
    }

    public List<Orderr> deleteOrder(Long id){
        orderRepository.deleteById(id);
        return listAllOrders();
    }
}
