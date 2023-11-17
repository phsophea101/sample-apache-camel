package com.sophea.spring.camel.rest.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@Setter
@Getter
@ToString
public class OrderDto {
    private String id = UUID.randomUUID().toString();
    private List<CartItemDto> items;
    private String merchantId;
    private String userId;
    private String orderNumber;
    private BigDecimal total;
    private BigDecimal subTotal;
}
