package com.gungor.exchange.validators;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = ConversionListValidator.class)
public @interface ConversionList {
    String message() default "at least one of the inputs shall be provided";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
