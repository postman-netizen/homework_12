package org.example.java_pro_homework8.services;

import jakarta.transaction.Transactional;
import org.example.java_pro_homework8.model.FixerResponse;
import org.example.java_pro_homework8.model.MainCurrency;
import org.example.java_pro_homework8.repo.MainCurrencyRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;

@Service
public class MainCurrencyService {
    @Value("${fixer.api.key}")
    private String apiKey;

    private final RestTemplate restTemplate = new RestTemplate();

    private MainCurrencyRepository mainCurrencyRepository;

    private String currentBase ;

    public MainCurrencyService(MainCurrencyRepository mainCurrencyRepository) {
        this.mainCurrencyRepository = mainCurrencyRepository;
    }

    @Transactional
    public Map<String, Double> convertAll(String newCurrency) {
        Map<String, Double> eurRates = loadEurRates();

        if (!eurRates.containsKey(newCurrency)) {
            throw new IllegalArgumentException("Unknown base currency: " + newCurrency);
        }

        double rateEurToSource = eurRates.get(newCurrency);

        this.currentBase = newCurrency.toUpperCase();

        if(newCurrency.equals("EUR")){
            return eurRates;
        }else {
            return eurRates.entrySet().stream()
                    .collect(Collectors.toMap(
                            Map.Entry::getKey,
                            entry -> entry.getValue() / rateEurToSource,
                            (oldValue, newValue) -> oldValue,
                            LinkedHashMap::new
                    ));
        }

    }
    @Transactional
    public void addCurrency(String newCurrency) {
        mainCurrencyRepository.save(new MainCurrency(newCurrency));
    }

    public String getBaseCurrency() {
        return this.currentBase;
    }


    private Map<String, Double> loadEurRates() {
        String url = "http://data.fixer.io/api/latest?access_key=" + apiKey;

        FixerResponse response = restTemplate.getForObject(url, FixerResponse.class);

        if (response == null || !response.isSuccess()) {
            throw new RuntimeException("Error loading currency rates from Fixer");
        }

        return response.getRates();
    }

}
