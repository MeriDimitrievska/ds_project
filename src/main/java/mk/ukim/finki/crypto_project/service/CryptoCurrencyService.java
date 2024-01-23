package mk.ukim.finki.crypto_project.service;

import mk.ukim.finki.crypto_project.repository.CryptoCurrencyRepository;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import mk.ukim.finki.crypto_project.model.CryptoCurrency;
import org.springframework.web.client.RestTemplate;

import org.springframework.http.HttpHeaders;

import java.time.LocalDate;
import java.util.*;

@Service
public class CryptoCurrencyService {
    //  This is the base URL for CoinGecko
    private final String COINGECKO_API_URL = "https://api.coingecko.com/api/v3";
    private final RestTemplate restTemplate;
    private final CryptoCurrencyRepository cryptoCurrencyRepository;

    public CryptoCurrencyService(CryptoCurrencyRepository cryptoCurrencyRepository) {
        this.restTemplate = new RestTemplate();
        this.cryptoCurrencyRepository = cryptoCurrencyRepository;
    }

    public void saveAndFetchCryptoData() {
        String apiUrl = COINGECKO_API_URL + "/coins/markets";
        HttpHeaders headers = new HttpHeaders();
        // Here I set the key for CoinGecko, this key gives me the opportunity
        // to access this API and get the information I need.
        headers.set("X-CoinGecko-Request", "CG-39tpPq7kuiYuSbnGRaUrqhLM");
        String vsCurrency = "usd";
        apiUrl += "?vs_currency=" + vsCurrency;
        HttpEntity<?> entity = new HttpEntity<>(headers);
        ResponseEntity<CryptoCurrency[]> response = restTemplate.exchange(
                apiUrl,
                HttpMethod.GET,
                entity,
                CryptoCurrency[].class
        );

        if (response.getStatusCode().is2xxSuccessful()) {
            CryptoCurrency[] cryptoCurrencies = response.getBody();
            if (cryptoCurrencies != null) {
                cryptoCurrencyRepository.deleteAll();
                cryptoCurrencyRepository.saveAll(Arrays.asList(cryptoCurrencies));
            }
        } else {
            System.out.println("Error: " + response.getStatusCodeValue());
        }

    }

    public String getAveragePrice() {
        double avg = 0;
        List<CryptoCurrency> all = cryptoCurrencyRepository.findAll();
        for (CryptoCurrency cryptoCurrency : all) {
            avg += cryptoCurrency.getCurrent_price();
        }
        avg /= all.size();
        return "Average price for cryptocurrencies on " + LocalDate.now() + " is " + avg + "$.\n";
    }

    public String getMinPrice() {
        List<CryptoCurrency> all = cryptoCurrencyRepository.findAll();
        int index = 0;
        double min = all.get(0).getCurrent_price();
        for (int i = 0; i < all.size(); i++) {
            if (all.get(i).getCurrent_price() < min) {
                min = all.get(i).getCurrent_price();
                index = i;
            }
        }
        return "Minimal Price is " + all.get(index).getCurrent_price() + "$ for cryptocurrency " + all.get(index).getName() + ".\n";
    }

    public String getMaxPrice() {
        List<CryptoCurrency> all = cryptoCurrencyRepository.findAll();
        int index = 0;
        double max = all.get(0).getCurrent_price();
        for (int i = 0; i < all.size(); i++) {
            if (all.get(i).getCurrent_price() > max) {
                max = all.get(i).getCurrent_price();
                index = i;
            }
        }
        return "Maximal Price is " + all.get(index).getCurrent_price() + "$ for cryptocurrency " + all.get(index).getName() + ".\n";
    }

    public String top5() {
        List<CryptoCurrency> all = cryptoCurrencyRepository.findAll();
        all.sort(Comparator.comparing(CryptoCurrency::getCurrent_price).reversed());
        StringBuilder sb = new StringBuilder();
        sb.append("Top 5 cryptocurrencies with highest price: \n");
        for (int i = 0; i < 5; i++)
            sb.append(all.get(i).getName()).append("\n");
        return sb.toString();
    }
}
