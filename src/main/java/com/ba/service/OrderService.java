package com.ba.service;

import com.ba.dto.OrderDTO;
import com.ba.mapper.OrderMapper;
import com.ba.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    public List<OrderDTO> listAllOrders(){
        return OrderMapper.INSTANCE.toDTOList(orderRepository.findAll());
//        return OrderConverter.convertOrderListToOrderListDTO(orderRepository.findAll());
    }

    public String addOrder(List<OrderDTO> orderDTO){
        orderRepository.saveAll(OrderMapper.INSTANCE.toList(orderDTO));
//        orderRepository.saveAll(OrderConverter.convertOrderListDTOToOrderList(orderDTO));
        return "Order Added";
    }

//    public void deleteOrder(Long id){
//        orderRepository.deleteById(id);
//    }
}
