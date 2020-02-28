package com.gungor.exchange.error;

import java.util.Date;
import java.util.Objects;

public class ConversionNotFoundException extends RuntimeException {

    public ConversionNotFoundException(Long conversitonId, Date startDate, Date endDate) {
        super(String.format("Conversions not found! conversitonId: %s startDate: %s endDate: %s",
                Objects.toString(conversitonId, ""), Objects.toString(startDate, ""),
                Objects.toString(endDate, "")
        ));
    }
}
