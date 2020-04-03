package com.tala.kafkaingress.controller;

import com.tala.kafkaingress.person.shcema.Person;
import io.confluent.kafka.serializers.KafkaAvroSerializer;
import org.apache.kafka.clients.producer.Callback;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.apache.kafka.common.serialization.StringSerializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Properties;

@RestController
public class KafkaController {

    private static final Logger LOGGER = LoggerFactory.getLogger(KafkaController.class);

    private KafkaTemplate<String, String> template;

    public KafkaController(KafkaTemplate<String, String> template) {
        this.template = template;
    }

    @GetMapping("/kafka/producer")
    public void producer(@RequestParam String message) {
        template.send("test_topic", message);
    }

    @PostMapping("/kafka/avroproducer")
    public String avroproducer(@RequestParam String message) {

        Properties properties = new Properties();
        properties.setProperty("bootstrap.servers", "broker:29092");
        properties.setProperty("acks", "1");
        properties.setProperty("retries", "10");

        properties.setProperty("key.serializer", StringSerializer.class.getName());

        properties.setProperty("value.serializer", KafkaAvroSerializer.class.getName());
        properties.setProperty("schema.registry.url", "http://schema-registry:8081");

        KafkaProducer<String, Person> kafkaProducer = new KafkaProducer<String, Person>(properties);
        String topic = "person_topic";

        Person personRecord = Person.newBuilder()
                .setFirstName("FirstName")
                .setLastName("LastName")
                .build();

        ProducerRecord<String, Person> producerRecord = new ProducerRecord<String, Person>(
                topic, personRecord
        );

        kafkaProducer.send(producerRecord, new Callback() {
            @Override
            public void onCompletion(RecordMetadata recordMetadata, Exception e) {
                if ( e == null)
                {
                    LOGGER.info(recordMetadata.toString());
                }
                else
                {
                    e.printStackTrace();

                }
            }
        });

        kafkaProducer.flush();
        kafkaProducer.close();

        return "Kafka Avro producer complete!";
    }

}