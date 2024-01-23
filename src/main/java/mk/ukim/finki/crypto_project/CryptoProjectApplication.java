package mk.ukim.finki.crypto_project;

import mk.ukim.finki.crypto_project.kafka.AvgPriceProducerConfig;
import mk.ukim.finki.crypto_project.kafka.CryptoCurrencyConsumerConfig;
import mk.ukim.finki.crypto_project.kafka.MaxPriceProducerConfig;
import mk.ukim.finki.crypto_project.kafka.MinPriceProducerConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CryptoProjectApplication {
    public static void main(String[] args) {
        SpringApplication.run(CryptoProjectApplication.class, args);
    }
}
