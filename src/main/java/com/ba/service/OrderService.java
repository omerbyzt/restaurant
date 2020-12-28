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

    public String addOrder(List<OrderDTO> orderDTO){
        orderRepository.saveAll(orderMapper.toList(orderDTO));
        return "Order Added";
    }

    public String temp(HttpServletRequest request){
        Locale currentLocale = request.getLocale();

        String countryCode = currentLocale.getCountry();
        String countryName = currentLocale.getDisplayCountry();

        String langCode = currentLocale.getLanguage();
        String langName = currentLocale.getDisplayLanguage();

        System.out.println(countryCode+ ": "+ countryName);
        System.out.println(langCode+": "+langName);

        System.out.println("================");
        String[] languages = Locale.getISOLanguages();

        for(String language : languages){
            Locale locale = new Locale(language);
            System.out.println(language + ": "+ locale.getDisplayLanguage());
        }

        return null;
    }
}
