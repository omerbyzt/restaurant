package com.ba.service;

import com.ba.dto.CreditCardDTO;
import com.ba.dto.OrderAndCreditDTO;
import com.ba.dto.OrderDTO;
import com.ba.dto.OrderWrapper;
import com.ba.entity.CreditCard;
import com.ba.entity.Order;
import com.ba.entity.OrderItem;
import com.ba.exception.SystemException;
import com.ba.mapper.*;
import com.ba.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private OrderItemRepository orderItemRepository;

    @Autowired
    private PaymentTypeRepository paymentTypeRepository;

    @Autowired
    private CreditCardRepository creditCardRepository;

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private WaiterRepository waiterRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private OrderMapper orderMapper;

    @Autowired
    private PaymentTypeMapper paymentTypeMapper;

    @Autowired
    private CreditCardMapper creditCardMapper;

    @Autowired
    private OrderItemMapper orderItemMapper;

    @Autowired
    private CustomerMapper customerMapper;

    @Autowired
    private WaiterMapper waiterMapper;

    @Autowired
    private ProductMapper productMapper;

    @Transactional
    public ResponseEntity<String> addOrderAndCredit(OrderAndCreditDTO orderAndCreditDTO) {
        List<OrderWrapper> inputWrapper = orderAndCreditDTO.getOrderWrapperList();
        CreditCardDTO inputCreditCard = orderAndCreditDTO.getCreditCardDTO();
        Long paymentId = orderAndCreditDTO.getPaymentId();

        //CreditCard
        if (inputCreditCard.getNumber() != "") {
            CreditCard creditCard = creditCardRepository.findCreditCardByNumber(inputCreditCard.getNumber());
            if (creditCard == null) {
                saveCreditCard(inputCreditCard);
            }
        }

        //Order
        Order order = saveOrder(inputWrapper, paymentId);

        //OrderItem
        inputWrapper.forEach(item->{
            OrderItem orderItem = new OrderItem();

            orderItem.setProduct(productRepository.findById(item.getProductId()).get());
            orderItem.setPiece((long) item.getAmount());
            orderItem.setTotalPrice((long) item.getTotalPrice());
            orderItem.setOrder(order);
            orderItem.setTable(item.getTableName());

            orderItemRepository.save(orderItem);
        });

        return new ResponseEntity<>("Order and credit card added", HttpStatus.OK);
    }

    public Order saveOrder(List<OrderWrapper> inputWrapper, Long paymentId) {
        OrderDTO orderDTO = new OrderDTO();
        orderDTO.setPaymentType(paymentTypeMapper.toDTO(paymentTypeRepository.findById(paymentId).get()));
        if (inputWrapper.get(0).getCustomerId() != -1) {
            orderDTO.setCustomer(customerMapper.toDTO(customerRepository.findById(inputWrapper.get(0).getCustomerId()).get()));
        }
        if (inputWrapper.get(0).getWaiterID() != -1){
            orderDTO.setWaiter(waiterMapper.toDTO(waiterRepository.findById(inputWrapper.get(0).getWaiterID()).get()));
        }
        int totalAmount = inputWrapper.stream().mapToInt(OrderWrapper::getTotalPrice).sum();
        orderDTO.setTotalAmount(totalAmount);
        int totalCount = inputWrapper.stream().mapToInt(OrderWrapper::getAmount).sum();
        orderDTO.setTotalCount(totalCount);
        orderDTO.setOrderDate(new Timestamp(System.currentTimeMillis()));

        return orderRepository.save(orderMapper.toEntity(orderDTO));
    }

    public void saveCreditCard(CreditCardDTO inputCreditCard) {
        CreditCard creditCard = creditCardMapper.toEntity(inputCreditCard);
        creditCardRepository.save(creditCard);
    }
}
