package com.tala.kafkaingress;

import com.tala.kafkaingress.controller.HomeController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class KafkaIngressApplication {

    private static final Logger logger = LoggerFactory.getLogger(KafkaIngressApplication.class);

    public static void main(String[] args) {


        SpringApplication.run(KafkaIngressApplication.class, args);
        logger.info("Kafka ingress service started");
    }

}
