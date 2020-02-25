package com.gungor.exchange.dto;

public class ExchangeApiRequest<T> {

    private T requestBody;

    public ExchangeApiRequest() {
    }

    public T getRequestBody() {
        return requestBody;
    }

    public void setRequestBody(T requestBody) {
        this.requestBody = requestBody;
    }

    @Override
    public String toString() {
        return "ExchangeApiRequest{" +
                "requestBody=" + requestBody +
                '}';
    }
}
