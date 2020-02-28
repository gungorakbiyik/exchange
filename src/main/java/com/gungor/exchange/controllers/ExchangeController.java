package com.gungor.exchange.controllers;


import com.gungor.exchange.domain.ConversionRec;
import com.gungor.exchange.dto.*;
import com.gungor.exchange.services.ExchangeService;
import com.gungor.exchange.validators.Currency;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.DecimalMin;
import java.util.List;

@RestController
@Validated
public class ExchangeController {

    private final ExchangeService exchangeService;

    public ExchangeController(ExchangeService exchangeService) {
        this.exchangeService = exchangeService;
    }

    @GetMapping("/rate")
    public Double rate(@Currency(message = "invalid base currency") @RequestParam(name = "base") String baseCurrency,
                       @Currency(message = "invalid target currency") @RequestParam(name = "target") String targetCurrency) {
        return exchangeService.exchangeRate(baseCurrency, targetCurrency);
    }

    @PostMapping("/rate")
    public Double rate(@Valid @RequestBody CurrencyPair currencyPair) {
        return exchangeService.exchangeRate(currencyPair);
    }

    @GetMapping("/currencies")
    public Iterable<String> supportedCurrencies() {
        return exchangeService.supportedCurrencies();
    }

    @GetMapping("/conversion")
    public Double conversion(@Currency(message = "invalid base currency") @RequestParam(name = "base") String baseCurrency,
                             @DecimalMin("0.01") @RequestParam(name = "amount") Double amount,
                             @Currency(message = "invalid base currency") @RequestParam(name = "target") String targetCurrency) {
        return exchangeService.conversion(baseCurrency, amount, targetCurrency);
    }

    @PostMapping("/conversion")
    public ConversionResponse conversion(@Valid @RequestBody ConversionRequest request) {
        return exchangeService.conversion(request);
    }

    @PostMapping("/conversion-list")
    public PageDto<List<ConversionRec>> conversionList(@Valid @RequestBody ConversionListRequest request) {
        return exchangeService.conversionList(request);
    }
}