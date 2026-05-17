package org.example.java_pro_homework8.controllers;

import org.example.java_pro_homework8.services.MainCurrencyService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

@Controller
public class MainCurrencyController {

    private MainCurrencyService mainCurrencyService;

    public MainCurrencyController(MainCurrencyService mainCurrencyService) {
        this.mainCurrencyService = mainCurrencyService;
    }

    @PostMapping("/api/admin/set-base")
    @ResponseBody
    public Map<String, Object> setMainCurrency(@RequestParam String baseCurrency) {
        Map<String, Double> rates = mainCurrencyService.convertAll(baseCurrency);

        Map<String, Object> response = new HashMap<>();
        response.put("base", baseCurrency.toUpperCase());
        response.put("rates", rates);

        return response;
    }

    @GetMapping("/api/admin/current-rates")
    @ResponseBody
    public Map<String, Object> getMapCurrentCurrency() {
        String base = mainCurrencyService.getBaseCurrency();
        Map<String, Double> rates = mainCurrencyService.convertAll(base);
        mainCurrencyService.addCurrency(base);

        Map<String, Object> response = new HashMap<>();
        response.put("base", base);
        response.put("rates", rates);
        return response;
    }


}
