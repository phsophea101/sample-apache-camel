package com.sophea.spring.camel.rest.service;

import com.sophea.spring.camel.rest.model.CartDto;
import com.sophea.spring.camel.rest.model.CartItemDto;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CartService {
    public CartDto calculateCart(Object request) throws Exception {
        if (!(request instanceof CartItemDto))
            throw new Exception("Unexpected error.");
        CartDto cart = this.fetchCart();
        CartItemDto item = (CartItemDto) request;
        Optional<CartItemDto> itemAvailable = cart.getItems().stream().filter(i -> i.getSkuId().equalsIgnoreCase(item.getSkuId())).findFirst();
        if (itemAvailable.isEmpty())
            cart.getItems().add(item);
        else {
            for (CartItemDto i : cart.getItems()) {
                if (i.getSkuId().equalsIgnoreCase(item.getSkuId()))
                    i.setQty(i.getQty().add(item.getQty()));
            }
        }
//        DB save
        return cart;
    }

    public CartDto fetchCart() {
        List<CartItemDto> items = new ArrayList<>();
        items.add(new CartItemDto("SKU-001", BigDecimal.valueOf(2)));
        items.add(new CartItemDto("SKU-002", BigDecimal.valueOf(1)));
        CartDto cart = new CartDto();
        cart.setItems(items);
        return cart;
    }

    public void validateSKU(Object request) throws Exception {
        List<CartItemDto> items = new ArrayList<>();
        items.add(new CartItemDto("SKU-001", BigDecimal.valueOf(1000)));
        items.add(new CartItemDto("SKU-002", BigDecimal.valueOf(400)));
        items.add(new CartItemDto("SKU-003", BigDecimal.valueOf(1000)));
        items.add(new CartItemDto("SKU-004", BigDecimal.valueOf(400)));
        items.add(new CartItemDto("SKU-005", BigDecimal.valueOf(1000)));
        items.add(new CartItemDto("SKU-006", BigDecimal.valueOf(400)));
        items.add(new CartItemDto("SKU-007", BigDecimal.valueOf(1000)));
        items.add(new CartItemDto("SKU-008", BigDecimal.valueOf(400)));
        if (request instanceof CartItemDto) {
            CartItemDto item = (CartItemDto) request;
            if (!items.contains(item))
                throw new Exception(String.format("Record not found for SKU [%s].", item.getSkuId()));
            Optional<CartItemDto> itemAvailable = items.stream().filter(i -> (i.getSkuId().equalsIgnoreCase(item.getSkuId()) && i.getQty().compareTo(item.getQty()) >= 0)).findFirst();
            if (itemAvailable.isEmpty())
                throw new Exception(String.format("SKU not available or out of stock [%s].", item.getSkuId()));
        }

    }
}
