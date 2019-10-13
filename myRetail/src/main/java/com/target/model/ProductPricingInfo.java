package com.target.model;

import java.math.BigDecimal;

public class ProductPricingInfo {
    private int id;
    private BigDecimal price;
    private String currencyCode;

    public ProductPricingInfo() {}

    public ProductPricingInfo(int id, BigDecimal price, String currencyCode) {
        this.id = id;
        this.price = price;
        this.currencyCode = currencyCode;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getCurrencyCode() {
        return currencyCode;
    }

    public void setCurrencyCode(String currencyCode) {
        this.currencyCode = currencyCode;
    }

    @Override
    public String toString() {
        return "price = " + price + ", currencyCode = " + currencyCode;
    }
}
