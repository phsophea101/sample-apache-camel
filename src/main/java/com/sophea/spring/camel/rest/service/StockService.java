package com.sophea.spring.camel.rest.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class StockService {
    private static final Logger LOGGER = LoggerFactory.getLogger(StockService.class);

    public void executeStock(Object data) {
        LOGGER.info("Stock executed ...");
    }
}
