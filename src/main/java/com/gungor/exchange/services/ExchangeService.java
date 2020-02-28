package com.gungor.exchange.services;

import com.gungor.exchange.domain.ConversionRec;
import com.gungor.exchange.dto.*;

import java.util.List;

public interface ExchangeService {

    Iterable<String> supportedCurrencies();

    Double exchangeRate(String baseCurrency, String targetCurrency);

    Double conversion(String baseCurrency, Double amount, String targetCurrency);

    Double exchangeRate(CurrencyPair currencyPair);

    ConversionResponse conversion(ConversionRequest conversionRequest);

    PageDto<List<ConversionRec>> conversionList(ConversionListRequest request);
}
