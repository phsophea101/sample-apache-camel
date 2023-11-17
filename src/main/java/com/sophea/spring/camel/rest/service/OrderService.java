package com.sophea.spring.camel.rest.service;

import com.sophea.spring.camel.rest.model.CartDto;
import com.sophea.spring.camel.rest.model.OrderDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class OrderService {
    private static final Logger LOGGER = LoggerFactory.getLogger(OrderService.class);

    public OrderDto generateOrder(Object data) {
        LOGGER.info("Order notified prepare...");
        OrderDto order = new OrderDto();
        if (data instanceof CartDto) {
            CartDto cart = (CartDto) data;
            order.setOrderNumber("ORD-00000001");
            order.setTotal(BigDecimal.valueOf(1000));
            order.setSubTotal(BigDecimal.valueOf(10200));
            order.setUserId(cart.getUserId());
            order.setMerchantId(cart.getMerchantId());
            order.setItems(cart.getItems());
        }
        return order;
    }
}
