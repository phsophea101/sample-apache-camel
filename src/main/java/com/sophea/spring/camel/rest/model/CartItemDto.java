package com.sophea.spring.camel.rest.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.math.BigDecimal;
import java.util.Objects;

@Setter
@Getter
@ToString
@AllArgsConstructor
public class CartItemDto {
    private String skuId;
    private BigDecimal qty;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CartItemDto)) return false;
        CartItemDto that = (CartItemDto) o;
        return skuId.equals(that.skuId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(skuId);
    }

}
