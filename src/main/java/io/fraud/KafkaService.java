package io.fraud;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.fraud.consumer.KafkaMessageConsumer;
import io.fraud.producer.KafkaMessageProducer;
import lombok.SneakyThrows;
import org.apache.kafka.clients.producer.RecordMetadata;

public class KafkaService {

    private final KafkaMessageProducer kafkaMessageProducer;
    private final KafkaMessageConsumer messageConsumer;
    private final ObjectMapper objectMapper = new ObjectMapper();

    public KafkaService(String server) {
        this.kafkaMessageProducer = new KafkaMessageProducer(server);
        this.messageConsumer = new KafkaMessageConsumer(server);
    }

    public KafkaMessageConsumer consumer() {
        return messageConsumer;
    }

    @SneakyThrows
    public RecordMetadata send(String topic, Object message){
        return send("test", objectMapper.writeValueAsString(message));
    }
    public RecordMetadata send(String message){
        return send("test", message);
    }

    public RecordMetadata send(String topic, String message){
       return kafkaMessageProducer.send(topic, message);
    }


    public void subscribe(String topic) {
        messageConsumer.subscribe(topic);
        messageConsumer.consume();
    }

    public KafkaRecord waitForMessage(String message) {
        return messageConsumer.waitForMessage(message);
    }
}
