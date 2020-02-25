package com.gungor.exchange.dto;

public class ConversionResponse {
    private Double amount;
    private Long transactionId;

    public ConversionResponse() {
    }

    public ConversionResponse(Double amount, Long transactionId) {
        this.amount = amount;
        this.transactionId = transactionId;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public Long getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(Long transactionId) {
        this.transactionId = transactionId;
    }

    @Override
    public String toString() {
        return "ConversionResponse{" +
                "amount=" + amount +
                ", transactionId=" + transactionId +
                '}';
    }
}
