package com.ba.mapper;

import com.ba.dto.OrderDTO;
import com.ba.entity.Orderr;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2020-12-25T23:13:42+0300",
    comments = "version: 1.3.1.Final, compiler: javac, environment: Java 11.0.9 (Oracle Corporation)"
)
public class OrderMapperImpl implements OrderMapper {

    @Override
    public List<Orderr> toList(List<OrderDTO> orderDTOList) {
        if ( orderDTOList == null ) {
            return null;
        }

        List<Orderr> list = new ArrayList<Orderr>( orderDTOList.size() );
        for ( OrderDTO orderDTO : orderDTOList ) {
            list.add( orderDTOToOrderr( orderDTO ) );
        }

        return list;
    }

    @Override
    public List<OrderDTO> toDTOList(List<Orderr> orderList) {
        if ( orderList == null ) {
            return null;
        }

        List<OrderDTO> list = new ArrayList<OrderDTO>( orderList.size() );
        for ( Orderr orderr : orderList ) {
            list.add( orderrToOrderDTO( orderr ) );
        }

        return list;
    }

    protected Orderr orderDTOToOrderr(OrderDTO orderDTO) {
        if ( orderDTO == null ) {
            return null;
        }

        Orderr orderr = new Orderr();

        orderr.setOrderID( orderDTO.getOrderID() );
        orderr.setPId( orderDTO.getPId() );
        orderr.setName( orderDTO.getName() );
        orderr.setPrice( orderDTO.getPrice() );
        orderr.setAmount( orderDTO.getAmount() );
        orderr.setTableName( orderDTO.getTableName() );
        orderr.setWaiterID( orderDTO.getWaiterID() );
        orderr.setOrderDate( orderDTO.getOrderDate() );
        orderr.setDeleted( orderDTO.isDeleted() );

        return orderr;
    }

    protected OrderDTO orderrToOrderDTO(Orderr orderr) {
        if ( orderr == null ) {
            return null;
        }

        OrderDTO orderDTO = new OrderDTO();

        orderDTO.setOrderID( orderr.getOrderID() );
        orderDTO.setPId( orderr.getPId() );
        orderDTO.setName( orderr.getName() );
        orderDTO.setPrice( orderr.getPrice() );
        orderDTO.setAmount( orderr.getAmount() );
        orderDTO.setTableName( orderr.getTableName() );
        orderDTO.setOrderDate( orderr.getOrderDate() );
        orderDTO.setWaiterID( orderr.getWaiterID() );
        orderDTO.setDeleted( orderr.isDeleted() );

        return orderDTO;
    }
}
