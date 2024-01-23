package mk.ukim.finki.crypto_project.kafka;

import mk.ukim.finki.crypto_project.CryptoProjectApplication;
import mk.ukim.finki.crypto_project.service.CryptoCurrencyService;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Configuration;

import java.util.Properties;

@Configuration
public class MaxPriceProducerConfig {
    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(CryptoProjectApplication.class, args);
        CryptoCurrencyService cryptoCurrencyService = context.getBean(CryptoCurrencyService.class);
        Properties props = new Properties();
        props.put("bootstrap.servers", "localhost:9092");
        props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");

        try (Producer<String, String> producer = new KafkaProducer<>(props)) {
            String topic = "topic-test";
            cryptoCurrencyService.saveAndFetchCryptoData();
            String message = cryptoCurrencyService.getMaxPrice();
            ProducerRecord<String, String> record = new ProducerRecord<>(topic, message);
            producer.send(record);
        }
    }
}
