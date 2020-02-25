package com.gungor.exchange.services;

import com.gungor.exchange.domain.ConversionRec;
import com.gungor.exchange.dto.ConversionRequest;
import com.gungor.exchange.dto.ConversionResponse;
import com.gungor.exchange.dto.CurrencyPair;
import com.gungor.exchange.integrations.ExchangeServiceProvider;
import com.gungor.exchange.repositories.ConversionRepository;
import org.springframework.stereotype.Service;

@Service
public class ExchangeServiceImpl implements ExchangeService {

    private final ExchangeServiceProvider provider;
    private final ConversionRepository conversionRepo;

    public ExchangeServiceImpl(ExchangeServiceProvider provider, ConversionRepository conversionRepo) {
        this.provider = provider;
        this.conversionRepo = conversionRepo;
    }

    @Override
    public Iterable<String> supportedCurrencies() {
        return provider.getSupportedCurrencies();
    }

    @Override
    public Double exchangeRate(String baseCurrency, String targetCurrency) {
        return provider.exchangeRate(baseCurrency, targetCurrency);
    }

    private Double calc(Double rate, Double amount) {
        return rate*amount;
    }

    @Override
    public Double conversion(String baseCurrency, Double amount, String targetCurrency) {
        Double rate = provider.exchangeRate(baseCurrency, targetCurrency);
        return calc(rate, amount);
    }

    @Override
    public Double exchangeRate(CurrencyPair currencyPair) {
        return exchangeRate(currencyPair.getBaseCurrency(), currencyPair.getTargetCurrency());
    }

    @Override
    public ConversionResponse conversion(ConversionRequest conversionRequest) {
        Double rate = exchangeRate(conversionRequest.getCurrencyPair().getBaseCurrency(), conversionRequest.getCurrencyPair().getTargetCurrency());
        Double convertedAmount = calc(rate, conversionRequest.getAmount());

        ConversionRec rec = new ConversionRec();
        rec.setBaseCurrency(conversionRequest.getCurrencyPair().getBaseCurrency());
        rec.setTargetCurrency(conversionRequest.getCurrencyPair().getTargetCurrency());
        rec.setAmount(conversionRequest.getAmount());
        rec.setRate(rate);
        rec.setConvertedAmount(convertedAmount);
        conversionRepo.save(rec);

        return new ConversionResponse(convertedAmount, rec.getId());
    }

    @Override
    public void conversionList() {
        //
    }
}
