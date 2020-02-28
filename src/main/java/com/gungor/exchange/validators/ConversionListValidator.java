package com.gungor.exchange.validators;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class ConversionListValidator implements ConstraintValidator<ConversionList, com.gungor.exchange.dto.ConversionListRequest> {
    public void initialize(ConversionList constraint) {
    }

    public boolean isValid(com.gungor.exchange.dto.ConversionListRequest request, ConstraintValidatorContext context) {
        return !(request.getTransactionId() == null && request.getStartDate() == null &&
                request.getEndDate() == null);
    }
}
