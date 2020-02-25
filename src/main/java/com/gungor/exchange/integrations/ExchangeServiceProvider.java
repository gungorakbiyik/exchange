package com.gungor.exchange.integrations;

import java.util.List;

public interface ExchangeServiceProvider {

    List<String> getSupportedCurrencies();

    Double exchangeRate(String baseCurrency, String targetCurrency);

}
