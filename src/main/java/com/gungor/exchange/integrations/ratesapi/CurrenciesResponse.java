package com.gungor.exchange.integrations.ratesapi;

import java.util.Map;

public class CurrenciesResponse {

    private String base;
    private Map<String, Double> rates;
    private String error;

    public CurrenciesResponse() {
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

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    @Override
    public String toString() {
        return "CurrenciesResponse{" +
                "base='" + base + '\'' +
                ", rates=" + rates +
                ", error='" + error + '\'' +
                '}';
    }
}
