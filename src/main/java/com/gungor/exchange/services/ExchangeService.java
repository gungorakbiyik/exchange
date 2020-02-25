package com.gungor.exchange.services;

import com.gungor.exchange.dto.ConversionRequest;
import com.gungor.exchange.dto.ConversionResponse;
import com.gungor.exchange.dto.CurrencyPair;

public interface ExchangeService {

    Iterable<String> supportedCurrencies();

    Double exchangeRate(String baseCurrency, String targetCurrency);

    Double conversion(String baseCurrency, Double amount, String targetCurrency);

    Double exchangeRate(CurrencyPair currencyPair);

    ConversionResponse conversion(ConversionRequest conversionRequest);

    void conversionList();
}
