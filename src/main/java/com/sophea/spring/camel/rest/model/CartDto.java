package com.sophea.spring.camel.rest.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Setter
@Getter
@ToString
public class CartDto {
    private List<CartItemDto> items;
    private String merchantId;
    private String userId;
}
