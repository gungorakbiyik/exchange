package com.gungor.exchange.validators;

import com.gungor.exchange.integrations.ExchangeServiceProvider;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.List;

public class CurrencyValidator implements ConstraintValidator<Currency, String> {

    private final ExchangeServiceProvider provider;
    private List<String> supportedCurrencies;

    public CurrencyValidator(ExchangeServiceProvider provider) {
        this.provider = provider;
    }

    @Override
    public void initialize(Currency constraintAnnotation) {
        supportedCurrencies = provider.getSupportedCurrencies();
    }

    @Override
    public boolean isValid(String currency, ConstraintValidatorContext constraintValidatorContext) {
        return supportedCurrencies.contains(currency);
    }
}
