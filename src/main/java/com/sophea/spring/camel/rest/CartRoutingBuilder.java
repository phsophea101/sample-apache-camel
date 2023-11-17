package com.sophea.spring.camel.rest;

import com.sophea.spring.camel.rest.service.CartService;
import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Service;

@Service
public class CartRoutingBuilder extends RouteBuilder {
    @Override
    public void configure() throws Exception {
        from(ServiceConstants.CART_SERVICE_ENDPOINT)
                .routeId(ServiceConstants.CART_ROUTE_ID)
                .log(">>> ${body}")
                .bean(CartService.class, "validateSKU")
                .bean(CartService.class, "calculateCart");
    }
}
