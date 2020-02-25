package com.gungor.exchange.dto;

public class ConversionRequest {
    private CurrencyPair currencyPair;
    private Double amount;

    public ConversionRequest() {
    }

    public CurrencyPair getCurrencyPair() {
        return currencyPair;
    }

    public void setCurrencyPair(CurrencyPair currencyPair) {
        this.currencyPair = currencyPair;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    @Override
    public String toString() {
        return "ConversionDto{" +
                "currencyPair=" + currencyPair +
                ", amount=" + amount +
                '}';
    }
}
