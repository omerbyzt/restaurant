package com.ba.converter;

import com.ba.dto.OrderDTO;
import com.ba.entity.Orderr;

import java.util.ArrayList;
import java.util.List;

public class OrderConverter {

    public static List<OrderDTO> convertOrderListToOrderListDTO(List<Orderr> orderList){
        List<OrderDTO> orderListDTO = new ArrayList<>();

        for (Orderr orderListItem:orderList){
            OrderDTO orderDTO = new OrderDTO();
            orderDTO.setOrderID(orderListItem.getOrderID());
            orderDTO.setName(orderListItem.getName());
            orderDTO.setOrderDate(orderListItem.getOrderDate());
            orderDTO.setpId(orderListItem.getpId());
            orderDTO.setAmount(orderListItem.getAmount());
            orderDTO.setPrice(orderListItem.getPrice());
            orderDTO.setTableName(orderListItem.getTableName());
            orderDTO.setWaiterID(orderListItem.getWaiterID());
            orderListDTO.add(orderDTO);
        }
        return orderListDTO;
    }

    public static List<Orderr> convertOrderListDTOToOrderList(List<OrderDTO> orderDTO){
        List<Orderr> orderList = new ArrayList<>();
        for(int i=0; i<orderDTO.size(); i++){
            Orderr orderr = new Orderr();
            orderr.setOrderID(orderDTO.get(i).getOrderID());
            orderr.setName(orderDTO.get(i).getName());
            orderr.setOrderDate(orderDTO.get(i).getOrderDate());
            orderr.setpId(orderDTO.get(i).getpId());
            orderr.setAmount(orderDTO.get(i).getAmount());
            orderr.setPrice(orderDTO.get(i).getPrice());
            orderr.setTableName(orderDTO.get(i).getTableName());
            orderr.setWaiterID(orderDTO.get(i).getWaiterID());
            orderList.add(orderr);
        }
        return orderList;
    }
}
