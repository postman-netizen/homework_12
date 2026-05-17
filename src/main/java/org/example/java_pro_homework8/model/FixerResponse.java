package org.example.java_pro_homework8.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.util.Map;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class FixerResponse {
    private boolean success;
    private String base;
    private Map<String, Double> rates;

    public FixerResponse(String base, Map<String, Double> rates, boolean success) {
        this.base = base;
        this.rates = rates;
        this.success = success;
    }

    public FixerResponse() {
    }

    public String getBase() {
        return base;
    }

    public void setBase(String base) {
        this.base = base;
    }

    public Map<String, Double> getRates() {
        return rates;
    }

    public void setRates(Map<String, Double> rates) {
        this.rates = rates;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }
}
