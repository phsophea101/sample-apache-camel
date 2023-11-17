package com.sophea.spring.camel.rest;

import com.sophea.spring.camel.rest.model.CartItemDto;
import org.apache.camel.ProducerTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("api")
public class MyRestController {
    @Autowired
    private ProducerTemplate producer;

    @PostMapping("/cart/add")
    @ResponseBody
    public ResponseEntity<Object> addCart(@RequestBody CartItemDto requestBody) {
        producer.setDefaultEndpointUri(ServiceConstants.CART_SERVICE_ENDPOINT);
        producer.start();
        Object body = producer.requestBodyAndHeaders(requestBody, CamelUtils.toHeadersMap(ServiceConstants.CART_ROUTE_ID, requestBody.getSkuId(), ServiceConstants.MERCHANT_ID, "1001", ServiceConstants.USER_ID, "2002"));
        producer.stop();
        return ResponseEntity.status(200).body(body);
    }

    @PostMapping("order/checkout")
    @ResponseBody
    public ResponseEntity<Object> checkout() {
        producer.setDefaultEndpointUri(ServiceConstants.ORDER_SERVICE_ENDPOINT);
        producer.start();
        Object body = producer.requestBodyAndHeaders(null, CamelUtils.toHeadersMap(ServiceConstants.ORDER_ROUTE_ID, UUID.randomUUID().toString(), ServiceConstants.MERCHANT_ID, "1001", ServiceConstants.USER_ID, "2002"));
        producer.stop();
        return ResponseEntity.status(200).body(body);
    }
}
