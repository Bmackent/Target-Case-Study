package com.target.dynamodb.model;

import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.assertEquals;

public class ProductPricingInfoDOModelTest {
    @Test
    public void testProductPricingInfoDO() {
        ProductPricingInfoDO productPricingInfoDO = new ProductPricingInfoDO();
        productPricingInfoDO.setId(13860428);
        productPricingInfoDO.setPrice(new BigDecimal("12.99"));
        productPricingInfoDO.setCurrencyCode("USD");

        assertEquals(13860428, productPricingInfoDO.getId().intValue());
        assertEquals(12.99, productPricingInfoDO.getPrice().doubleValue(), 0.01);
        assertEquals("USD", productPricingInfoDO.getCurrencyCode());
    }
}
