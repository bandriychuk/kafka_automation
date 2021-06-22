package io.fraud.tests;

import io.fraud.KafkaRecord;
import io.fraud.consumer.KafkaMessageConsumer;
import io.fraud.producer.KafkaMessageProducer;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class BackendTests {


    @Test
    public void testCanWriteMessageToQueuingTransaction() {
        KafkaMessageProducer kafkaMessageProducer = new KafkaMessageProducer("localhost:9092");
        KafkaMessageConsumer messageConsumer = new KafkaMessageConsumer("localhost:9092");
        messageConsumer.subscribe("test");
        messageConsumer.consume();

        kafkaMessageProducer.send("test","Hello from Java 8");

        KafkaRecord receivedRecords = messageConsumer.waitForMessage("Hello from Java 8");

        assertThat(receivedRecords).isNotNull();
        assertThat(receivedRecords).isNotNull();
    }
}
