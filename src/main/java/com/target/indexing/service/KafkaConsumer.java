package com.target.indexing.service;

import com.target.indexing.document.ProductDocument;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

@Service
public class KafkaConsumer {
    private static final Logger log = LoggerFactory.getLogger(KafkaConsumer.class);

    private ProductService productService;

    @Autowired
    public void setProductService(ProductService productService) {
        this.productService = productService;
    }

    @KafkaListener(topics = "${kafka.topic}")
    public void listen(@Payload ProductDocument productDocument) {
        try {
            log.debug("Recieved product with id = {}", productDocument.getProduct_id());
            productService.createProductDocument(productDocument);
        } catch (Exception ex) {
            log.error("Pushing to el for product with id = {} failed with exception", productDocument.getProduct_id(), ex);
        }

    }
}
