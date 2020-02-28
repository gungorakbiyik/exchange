package com.gungor.exchange.dto;

import com.gungor.exchange.validators.Currency;

public class CurrencyPair {


    @Currency(message = "invalid base currency")
    private String baseCurrency;

    @Currency(message = "invalid target currency")
    private String targetCurrency;

    public CurrencyPair() {
    }

    public CurrencyPair(String baseCurrency, String targetCurrency) {
        this.baseCurrency = baseCurrency;
        this.targetCurrency = targetCurrency;
    }

    public String getBaseCurrency() {
        return baseCurrency;
    }

    public void setBaseCurrency(String baseCurrency) {
        this.baseCurrency = baseCurrency;
    }

    public String getTargetCurrency() {
        return targetCurrency;
    }

    public void setTargetCurrency(String targetCurrency) {
        this.targetCurrency = targetCurrency;
    }

    @Override
    public String toString() {
        return "CurrencyPair{" +
                "baseCurrency='" + baseCurrency + '\'' +
                ", targetCurrency='" + targetCurrency + '\'' +
                '}';
    }
}
