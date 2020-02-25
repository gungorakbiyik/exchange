package com.gungor.exchange.controllers;


import com.gungor.exchange.dto.*;
import com.gungor.exchange.services.ExchangeService;
import org.springframework.web.bind.annotation.*;

@RestController
public class ExchangeEndpoint {

    private final ExchangeService exchangeService;

    public ExchangeEndpoint(ExchangeService exchangeService) {
        this.exchangeService = exchangeService;
    }

    @GetMapping("/rate")
    public Double rate(@RequestParam(name = "base") String baseCurrency,
                       @RequestParam(name = "target") String targetCurrency) {
        return exchangeService.exchangeRate(baseCurrency, targetCurrency);
    }

    @PostMapping("/rate")
    public Double rate(@RequestBody ExchangeApiRequest<CurrencyPair> request) {
        return exchangeService.exchangeRate(request.getRequestBody());
    }

    @GetMapping("/currencies")
    public Iterable<String> supportedCurrencies() {
        return exchangeService.supportedCurrencies();
    }

    @GetMapping("/conversion")
    public Double conversion(@RequestParam(name = "base") String baseCurrency,
                       @RequestParam(name = "amount") Double amount,
                       @RequestParam(name = "target") String targetCurrency) {
        return exchangeService.conversion(baseCurrency, amount, targetCurrency);
    }

    @PostMapping("/conversion")
    public ExchangeApiResponse<ConversionResponse> conversion(@RequestBody ExchangeApiRequest<ConversionRequest> request) {
        return new ExchangeApiResponse<>(exchangeService.conversion(request.getRequestBody()));
    }




}
