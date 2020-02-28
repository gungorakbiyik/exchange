package com.gungor.exchange.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.gungor.exchange.validators.ConversionList;

import javax.validation.constraints.Min;
import java.util.Date;

@ConversionList(message = "at least one of the inputs shall be provided")
public class ConversionListRequest {
    @Min(1)
    private Long transactionId;
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS")
    private Date startDate;
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS")
    private Date endDate;
    private PageableDto page;

    public ConversionListRequest() {
    }

    public ConversionListRequest(Long transactionId, Date startDate, Date endDate) {
        this.transactionId = transactionId;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public Long getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(Long transactionId) {
        this.transactionId = transactionId;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public PageableDto getPage() {
        return page;
    }

    public void setPage(PageableDto page) {
        this.page = page;
    }

    @Override
    public String toString() {
        return "ConversionListRequest{" +
                "transactionId=" + transactionId +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                '}';
    }
}
