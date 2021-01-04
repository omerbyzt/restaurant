package com.ba.mapper;

import com.ba.dto.CustomerDTO;
import com.ba.dto.MediaDTO;
import com.ba.dto.OrderDTO;
import com.ba.dto.PaymentTypeDTO;
import com.ba.dto.WaiterDTO;
import com.ba.entity.Customer;
import com.ba.entity.Media;
import com.ba.entity.Order;
import com.ba.entity.PaymentType;
import com.ba.entity.Waiter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2021-01-03T17:09:17+0300",
    comments = "version: 1.3.1.Final, compiler: javac, environment: Java 11.0.9 (Oracle Corporation)"
)
@Component
public class OrderMapperImpl implements OrderMapper {

    @Override
    public Order toEntity(OrderDTO orderDTO) {
        if ( orderDTO == null ) {
            return null;
        }

        Order order = new Order();

        order.setId( orderDTO.getId() );
        order.setPaymentType( paymentTypeDTOToPaymentType( orderDTO.getPaymentType() ) );
        order.setCustomer( customerDTOToCustomer( orderDTO.getCustomer() ) );
        order.setWaiter( waiterDTOToWaiter( orderDTO.getWaiter() ) );
        order.setTotalAmount( orderDTO.getTotalAmount() );
        order.setTotalCount( orderDTO.getTotalCount() );
        order.setOrderDate( orderDTO.getOrderDate() );

        return order;
    }

    @Override
    public OrderDTO toDTO(Order order) {
        if ( order == null ) {
            return null;
        }

        OrderDTO orderDTO = new OrderDTO();

        orderDTO.setId( order.getId() );
        orderDTO.setPaymentType( paymentTypeToPaymentTypeDTO( order.getPaymentType() ) );
        orderDTO.setCustomer( customerToCustomerDTO( order.getCustomer() ) );
        orderDTO.setWaiter( waiterToWaiterDTO( order.getWaiter() ) );
        orderDTO.setTotalAmount( order.getTotalAmount() );
        orderDTO.setTotalCount( order.getTotalCount() );
        orderDTO.setOrderDate( order.getOrderDate() );

        return orderDTO;
    }

    @Override
    public List<Order> toEntityList(List<OrderDTO> orderDTOList) {
        if ( orderDTOList == null ) {
            return null;
        }

        List<Order> list = new ArrayList<Order>( orderDTOList.size() );
        for ( OrderDTO orderDTO : orderDTOList ) {
            list.add( toEntity( orderDTO ) );
        }

        return list;
    }

    @Override
    public List<OrderDTO> toDTOList(List<Order> orderList) {
        if ( orderList == null ) {
            return null;
        }

        List<OrderDTO> list = new ArrayList<OrderDTO>( orderList.size() );
        for ( Order order : orderList ) {
            list.add( toDTO( order ) );
        }

        return list;
    }

    protected PaymentType paymentTypeDTOToPaymentType(PaymentTypeDTO paymentTypeDTO) {
        if ( paymentTypeDTO == null ) {
            return null;
        }

        PaymentType paymentType = new PaymentType();

        paymentType.setId( paymentTypeDTO.getId() );
        paymentType.setType( paymentTypeDTO.getType() );

        return paymentType;
    }

    protected Media mediaDTOToMedia(MediaDTO mediaDTO) {
        if ( mediaDTO == null ) {
            return null;
        }

        Media media = new Media();

        media.setId( mediaDTO.getId() );
        media.setDeleted( mediaDTO.isDeleted() );
        media.setName( mediaDTO.getName() );
        byte[] fileContent = mediaDTO.getFileContent();
        if ( fileContent != null ) {
            media.setFileContent( Arrays.copyOf( fileContent, fileContent.length ) );
        }

        return media;
    }

    protected Customer customerDTOToCustomer(CustomerDTO customerDTO) {
        if ( customerDTO == null ) {
            return null;
        }

        Customer customer = new Customer();

        customer.setId( customerDTO.getId() );
        customer.setName( customerDTO.getName() );
        customer.setSurname( customerDTO.getSurname() );
        customer.setPhoneNumber( customerDTO.getPhoneNumber() );
        customer.setAddress( customerDTO.getAddress() );
        customer.setMedia( mediaDTOToMedia( customerDTO.getMedia() ) );

        return customer;
    }

    protected Waiter waiterDTOToWaiter(WaiterDTO waiterDTO) {
        if ( waiterDTO == null ) {
            return null;
        }

        Waiter waiter = new Waiter();

        waiter.setId( waiterDTO.getId() );
        waiter.setDeleted( waiterDTO.isDeleted() );
        waiter.setName( waiterDTO.getName() );
        waiter.setPhoneNumber( waiterDTO.getPhoneNumber() );
        waiter.setMail( waiterDTO.getMail() );
        waiter.setAddress( waiterDTO.getAddress() );
        waiter.setUrlToImage( waiterDTO.getUrlToImage() );
        waiter.setSalary( waiterDTO.getSalary() );

        return waiter;
    }

    protected PaymentTypeDTO paymentTypeToPaymentTypeDTO(PaymentType paymentType) {
        if ( paymentType == null ) {
            return null;
        }

        PaymentTypeDTO paymentTypeDTO = new PaymentTypeDTO();

        paymentTypeDTO.setId( paymentType.getId() );
        paymentTypeDTO.setType( paymentType.getType() );

        return paymentTypeDTO;
    }

    protected MediaDTO mediaToMediaDTO(Media media) {
        if ( media == null ) {
            return null;
        }

        MediaDTO mediaDTO = new MediaDTO();

        mediaDTO.setId( media.getId() );
        mediaDTO.setName( media.getName() );
        byte[] fileContent = media.getFileContent();
        if ( fileContent != null ) {
            mediaDTO.setFileContent( Arrays.copyOf( fileContent, fileContent.length ) );
        }
        mediaDTO.setDeleted( media.isDeleted() );

        return mediaDTO;
    }

    protected CustomerDTO customerToCustomerDTO(Customer customer) {
        if ( customer == null ) {
            return null;
        }

        CustomerDTO customerDTO = new CustomerDTO();

        if ( customer.getId() != null ) {
            customerDTO.setId( customer.getId() );
        }
        customerDTO.setName( customer.getName() );
        customerDTO.setSurname( customer.getSurname() );
        customerDTO.setPhoneNumber( customer.getPhoneNumber() );
        customerDTO.setAddress( customer.getAddress() );
        customerDTO.setMedia( mediaToMediaDTO( customer.getMedia() ) );

        return customerDTO;
    }

    protected WaiterDTO waiterToWaiterDTO(Waiter waiter) {
        if ( waiter == null ) {
            return null;
        }

        WaiterDTO waiterDTO = new WaiterDTO();

        waiterDTO.setId( waiter.getId() );
        waiterDTO.setName( waiter.getName() );
        waiterDTO.setPhoneNumber( waiter.getPhoneNumber() );
        waiterDTO.setMail( waiter.getMail() );
        waiterDTO.setAddress( waiter.getAddress() );
        waiterDTO.setUrlToImage( waiter.getUrlToImage() );
        waiterDTO.setSalary( waiter.getSalary() );
        waiterDTO.setDeleted( waiter.isDeleted() );

        return waiterDTO;
    }
}
