package mk.ukim.finki.crypto_project.kafka;

import org.apache.kafka.clients.consumer.Consumer;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;

import java.time.Duration;
import java.util.Collections;
import java.util.Properties;

public class CryptoCurrencyConsumerConfig {
    public static void main(String[] args) {
        Properties props = new Properties();
        props.put("bootstrap.servers", "localhost:9092");
        props.put("group.id", "my_group");
        props.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        props.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        try (Consumer<String, String> consumer = new KafkaConsumer<>(props)) {
            String topic = "topic-test";
            consumer.subscribe(Collections.singletonList(topic));

            while (true) {
                Thread.sleep(100);

                ConsumerRecords<String, String> records = consumer.poll(Duration.ofMillis(100));
                records.forEach(record -> {
                    System.out.println(record.value());
                });
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
