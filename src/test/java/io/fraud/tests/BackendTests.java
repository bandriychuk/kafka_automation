package io.fraud.tests;

import io.fraud.KafkaRecord;
import io.fraud.KafkaService;
import io.fraud.consumer.KafkaMessageConsumer;
import io.fraud.messages.DealMessage;
import io.fraud.messages.GeneratorMessage;
import io.fraud.producer.KafkaMessageProducer;
import org.apache.commons.lang3.RandomStringUtils;
import org.assertj.core.internal.bytebuddy.utility.RandomString;
import org.junit.jupiter.api.Test;

import java.util.Date;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class BackendTests {

    private final KafkaService kafkaService = new KafkaService("localhost:9092");

    @Test
    public void testCanWriteMessageToQueuingTransaction() {
        kafkaService.subscribe("test");
        kafkaService.send("test","Hello from Java 8");

        KafkaRecord receivedRecords = kafkaService.waitForMessage("Hello from Java 8");

        assertThat(receivedRecords).isNotNull();
        assertThat(receivedRecords).isNotNull();
    }

    @Test
    public void testApplicationCanProcessValidMessage() {
        GeneratorMessage generatorMessage = new GeneratorMessage();
        generatorMessage.setData(new Date().toString());
        generatorMessage.setAmount(2000);
        generatorMessage.setCurrency("EUR");
        generatorMessage.setSource(RandomStringUtils.randomAlphabetic(10));
        generatorMessage.setSource(RandomStringUtils.randomAlphabetic(10));

        kafkaService.subscribe("streaming.transactions.legit");
        kafkaService.send("queuing.transactions", generatorMessage);

        DealMessage dealMessage = kafkaService.waitForMessage("java12").valueAs(DealMessage.class);

        assertThat(dealMessage.getAmount()).isEqualTo(900.0);
        assertThat(dealMessage.getBaseCurrency()).isEqualTo("USD");
    }

}
