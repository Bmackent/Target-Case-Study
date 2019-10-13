package com.target.model;

import java.math.BigDecimal;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class ProductModelTest {
    @Test
    public void testProductModel() {
        ProductPricingInfo productPricingInfo = new ProductPricingInfo(13860428, new BigDecimal("12.99"), "USD");
        Product product = new Product(13860428, "The Big Lebowski (Blu-ray)", productPricingInfo);

        assertEquals(13860428, product.getId());
        assertEquals("The Big Lebowski (Blu-ray)", product.getName());
        assertEquals(13860428, product.getProductPricingInfo().getId());
        assertEquals(12.99, product.getProductPricingInfo().getPrice().doubleValue(),0.01);
        assertEquals("USD", product.getProductPricingInfo().getCurrencyCode());
    }
}
