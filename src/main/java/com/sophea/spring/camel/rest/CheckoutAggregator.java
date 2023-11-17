package com.sophea.spring.camel.rest;

import com.sophea.spring.camel.rest.model.CartDto;
import org.apache.camel.AggregationStrategy;
import org.apache.camel.Exchange;
import org.springframework.util.ObjectUtils;

public class CheckoutAggregator implements AggregationStrategy {

    @Override
    public Exchange aggregate(Exchange oldExchange, Exchange newExchange) {
        if (newExchange.getIn().getBody() instanceof CartDto) {
            CartDto cart = (CartDto) newExchange.getIn().getBody();
            cart.setMerchantId(newExchange.getIn().getHeader(ServiceConstants.MERCHANT_ID, String.class));
            cart.setUserId(newExchange.getIn().getHeader(ServiceConstants.USER_ID, String.class));
            if (ObjectUtils.isEmpty(oldExchange)) {
                newExchange.getMessage().setBody(cart, CartDto.class);
                return newExchange;
            }
            oldExchange.getMessage().setBody(cart, CartDto.class);
        }
        return oldExchange;
    }
}
