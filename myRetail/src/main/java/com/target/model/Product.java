package com.target.model;

public class Product {
    private int id;
    private String name;
    private ProductPricingInfo productPricingInfo;

    public Product() {}

    public Product(int id, String name, ProductPricingInfo productPricingInfo) {
        this.id = id;
        this.name = name;
        this.productPricingInfo = productPricingInfo;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ProductPricingInfo getProductPricingInfo() {
        return productPricingInfo;
    }

    public void setPrice(ProductPricingInfo productPricingInfo) {
        this.productPricingInfo = productPricingInfo;
    }

    @Override
    public String toString() {
        return "Product id = " + id + ", name = " + name +", " + productPricingInfo.toString();
    }
}