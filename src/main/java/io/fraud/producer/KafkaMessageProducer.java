package io.fraud.producer;

import org.apache.kafka.clients.producer.KafkaProducer;

public class KafkaMessageProducer {

    private static KafkaProducer<String, String> kafkaProducer;
    private final String bootStrapServer;

    public KafkaMessageProducer(String bootStrapServer) {
        this.bootStrapServer = bootStrapServer;
    }

}
