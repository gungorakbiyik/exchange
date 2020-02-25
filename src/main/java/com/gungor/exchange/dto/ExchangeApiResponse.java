package com.gungor.exchange.dto;

public class ExchangeApiResponse<T> {

    private T responseBody;

    public ExchangeApiResponse() {
    }

    public ExchangeApiResponse(T responseBody) {
        this.responseBody = responseBody;
    }

    public T getResponseBody() {
        return responseBody;
    }

    public void setResponseBody(T responseBody) {
        this.responseBody = responseBody;
    }

    @Override
    public String toString() {
        return "ExchangeApiResponse{" +
                "responseBody=" + responseBody +
                '}';
    }
}
