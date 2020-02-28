package com.gungor.exchange.services;

import com.gungor.exchange.domain.ConversionRec;
import com.gungor.exchange.dto.*;
import com.gungor.exchange.error.ConversionNotFoundException;
import com.gungor.exchange.integrations.ExchangeServiceProvider;
import com.gungor.exchange.repositories.ConversionRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExchangeServiceImpl implements ExchangeService {
    private Logger log = LoggerFactory.getLogger(ExchangeServiceImpl.class);
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
        Double rate = exchangeRate(conversionRequest.getBaseCurrency(), conversionRequest.getTargetCurrency());
        Double convertedAmount = calc(rate, conversionRequest.getAmount());

        ConversionRec rec = new ConversionRec();
        rec.setBaseCurrency(conversionRequest.getBaseCurrency());
        rec.setTargetCurrency(conversionRequest.getTargetCurrency());
        rec.setAmount(conversionRequest.getAmount());
        rec.setRate(rate);
        rec.setConvertedAmount(convertedAmount);
        conversionRepo.save(rec);

        return new ConversionResponse(convertedAmount, rec.getId());
    }

    @Override
    public PageDto<List<ConversionRec>> conversionList(ConversionListRequest request) {
        PageRequest pageRequest = PageRequest.of(0, 5);
        if (request.getPage()!= null) {
            pageRequest = PageRequest.of(request.getPage().getPage(), request.getPage().getSize());
        }

        Page<ConversionRec> page = conversionRepo.findByIdAndStartDateAndEndDate(request.getTransactionId(),
                request.getStartDate(), request.getEndDate(),
                pageRequest);

        if (page.getNumberOfElements() == 0) {
            throw new ConversionNotFoundException(request.getTransactionId(), request.getStartDate(), request.getEndDate());
        }

        PageDto<List<ConversionRec>> pageData = new PageDto<List<ConversionRec>>(page.getContent(),
                page.getTotalElements(), page.getTotalPages(), page.getNumberOfElements(), page.getNumber());

        log.info("totalElements: {}, totalPages: {}, number: {}, numberOfElements: {}, content: {}",
                page.getTotalElements(),
                page.getTotalPages(),
                page.getNumber(),
                page.getNumberOfElements(),
                page.getContent()
                );
        return pageData;
    }


}
