package com.target.dynamodb.access;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.model.CreateTableRequest;
import com.amazonaws.services.dynamodbv2.model.ProvisionedThroughput;
import com.amazonaws.services.dynamodbv2.util.TableUtils;
import com.target.dynamodb.model.ProductPricingInfoDO;
import com.target.dynamodb.repositories.ProductPricingInfoRepository;
import com.target.model.ProductPricingInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.math.BigDecimal;
import java.util.Optional;

@Component
public class ProductPricingDatabase {
    @Autowired
    private AmazonDynamoDB amazonDynamoDB;

    @Autowired
    private ProductPricingInfoRepository productPricingInfoRepository;

    @PostConstruct
    public void initializeDatabase() {
        //Create the table if it doesn't already exist
        DynamoDBMapper dynamoDBMapper = new DynamoDBMapper(amazonDynamoDB);
        CreateTableRequest tableRequest = dynamoDBMapper.generateCreateTableRequest(ProductPricingInfoDO.class);
        tableRequest.setProvisionedThroughput(new ProvisionedThroughput(1L, 1L));
        TableUtils.createTableIfNotExists(amazonDynamoDB, tableRequest);

        //populate the table with some data
        ProductPricingInfoDO productPricingInfoDO = new ProductPricingInfoDO();
        productPricingInfoDO.setId(13860428);
        productPricingInfoDO.setCurrencyCode("USD");
        productPricingInfoDO.setPrice(new BigDecimal("13.49"));
        productPricingInfoRepository.save(productPricingInfoDO);

        productPricingInfoDO = new ProductPricingInfoDO();
        productPricingInfoDO.setId(13860429);
        productPricingInfoDO.setCurrencyCode("EUR");
        productPricingInfoDO.setPrice(new BigDecimal("12.50"));
        productPricingInfoRepository.save(productPricingInfoDO);

        productPricingInfoDO = new ProductPricingInfoDO();
        productPricingInfoDO.setId(13860431);
        productPricingInfoDO.setCurrencyCode("INR");
        productPricingInfoDO.setPrice(new BigDecimal("19.99"));
        productPricingInfoRepository.save(productPricingInfoDO);
    }

    public Optional<ProductPricingInfoDO> getProductPricingInfo(int productId) {
        Integer productIdInteger = new Integer(productId);
        Optional<ProductPricingInfoDO> productPricingInfoDO = productPricingInfoRepository.findById(productIdInteger);
        return productPricingInfoDO;
    }

    public ProductPricingInfo putProductPricingInfo(int productId, ProductPricingInfo productPricingInfo) {
        ProductPricingInfoDO productPricingInfoDO = new ProductPricingInfoDO();
        productPricingInfoDO.setId(productId);
        productPricingInfoDO.setCurrencyCode(productPricingInfo.getCurrencyCode());
        productPricingInfoDO.setPrice(productPricingInfo.getPrice());
        productPricingInfoRepository.save(productPricingInfoDO);

        return productPricingInfo;
    }
}
