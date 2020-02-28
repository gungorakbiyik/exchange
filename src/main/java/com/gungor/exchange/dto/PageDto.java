package com.gungor.exchange.dto;

public class PageDto<T> {

    private T data;
    private long totalElements;
    private int totalPages;
    private int numberOfElements;
    private int number;

    public PageDto() {
    }

    public PageDto(T data, long totalElements, int totalPages, int numberOfElements, int number) {
        this.data = data;
        this.totalElements = totalElements;
        this.totalPages = totalPages;
        this.numberOfElements = numberOfElements;
        this.number = number;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public long getTotalElements() {
        return totalElements;
    }

    public void setTotalElements(long totalElements) {
        this.totalElements = totalElements;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }

    public int getNumberOfElements() {
        return numberOfElements;
    }

    public void setNumberOfElements(int numberOfElements) {
        this.numberOfElements = numberOfElements;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    @Override
    public String toString() {
        return "PageDto{" +
                "data=" + data +
                ", totalElements=" + totalElements +
                ", totalPages=" + totalPages +
                ", numberOfElements=" + numberOfElements +
                '}';
    }
}
