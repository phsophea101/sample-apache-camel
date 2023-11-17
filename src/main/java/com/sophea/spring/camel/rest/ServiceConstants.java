package com.sophea.spring.camel.rest;

public final class ServiceConstants {

    //    cart
    public static final String CART_SERVICE_ENDPOINT = "direct:add-cart";
    public static final String ORDER_SERVICE_ENDPOINT = "direct:order-checkout";
    public static final String ORDER_ROUTE_ID = "order-checkout-id";
    public static final String CART_ROUTE_ID = "cart-route-id";
//    file

    public static final String MERCHANT_ID = "merchant-id";
    public static final String USER_ID = "user-id";

    private ServiceConstants() {

    }
}
