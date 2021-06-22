package io.fraud;

import org.apache.kafka.clients.consumer.ConsumerRecord;

public class KafkaRecord {

    private final ConsumerRecord<String, String> record;

    public KafkaRecord(ConsumerRecord<String, String> record) {
        this.record = record;
    }



}
