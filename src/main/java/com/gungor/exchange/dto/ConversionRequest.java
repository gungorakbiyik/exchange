package com.gungor.exchange.dto;

import javax.validation.constraints.DecimalMin;

public class ConversionRequest extends CurrencyPair {
    @DecimalMin("0.01")
    private Double amount;

    public ConversionRequest() {
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    @Override
    public String toString() {
        return "ConversionRequest{" +
                "baseCurrency='" + getBaseCurrency() + '\'' +
                ", targetCurrency='" + getTargetCurrency() + '\'' +
                ", amount=" + amount +
                '}';
    }
}
