package com.sophea.spring.camel.rest;

import com.sophea.spring.camel.rest.service.*;
import org.apache.camel.LoggingLevel;
import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Service;

@Service
public class CheckoutRoutingBuilder extends RouteBuilder {
    @Override
    public void configure() throws Exception {
        from(ServiceConstants.ORDER_SERVICE_ENDPOINT)
                .routeId(ServiceConstants.ORDER_ROUTE_ID)
                .bean(CartService.class, "validateSKU")
                .log(LoggingLevel.INFO, "com.sophea.spring.camel.rest.CheckoutRoutingBuilder", "before fetch cart ==> ${body}")
                .bean(CartService.class, "fetchCart")
                .log(LoggingLevel.INFO, "com.sophea.spring.camel.rest.CheckoutRoutingBuilder", "after fetch cart ==> ${body}")
                .aggregate(header(ServiceConstants.ORDER_ROUTE_ID), new CheckoutAggregator())
                .completionSize(1)
                .log(LoggingLevel.INFO, "com.sophea.spring.camel.rest.CheckoutRoutingBuilder", "after aggregator ==> ${body}")
                .bean(OrderService.class, "generateOrder")
                .log(LoggingLevel.INFO, "com.sophea.spring.camel.rest.CheckoutRoutingBuilder", "after generate order ==> ${body}")
                .bean(PaymentService.class, "execute")
                .bean(StockService.class, "executeStock")
                .bean(NotifyService.class, "notifyMessage");
    }
}
