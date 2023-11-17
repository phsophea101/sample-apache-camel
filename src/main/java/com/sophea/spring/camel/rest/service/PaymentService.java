package com.sophea.spring.camel.rest.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class PaymentService {
    private static final Logger LOGGER = LoggerFactory.getLogger(PaymentService.class);

    public void execute(Object data) {
        LOGGER.info("Payment executed ...");
    }
}
