package com.gungor.exchange.dto;

import javax.validation.constraints.Min;

public class PageableDto {
    @Min(value = 0, message = "Page index must not be less than zero!")
    private int page;
    @Min(value = 1, message = "Page size must not be less than one!")
    private int size;

    public PageableDto() {
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    @Override
    public String toString() {
        return "PageableDto{" +
                "page=" + page +
                ", size=" + size +
                '}';
    }
}
