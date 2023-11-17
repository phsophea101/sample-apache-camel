package com.sophea.spring.camel.rest.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class NotifyService {
    private static final Logger LOGGER = LoggerFactory.getLogger(NotifyService.class);

    public void notifyMessage(Object data) {
        LOGGER.info("Order notified message...");
    }
}
