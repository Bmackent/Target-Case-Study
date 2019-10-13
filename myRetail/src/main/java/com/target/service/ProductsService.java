package com.target.service;

import com.target.dynamodb.access.ProductPricingDatabase;
import com.target.dynamodb.model.ProductPricingInfoDO;
import com.target.model.ProductPricingInfo;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.target.model.Product;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;

@Service
public class ProductsService {
    @Autowired
    private ProductPricingDatabase productPricingDatabase;

    public Product getProduct(int productId) throws JSONException {
        Product product = new Product(productId, getName(productId), getPricingInfo(productId));
        return product;
    }

    public String getName(int productId) throws JSONException {
        String url = "https://redsky.target.com/v2/pdp/tcin/" + productId + "?excludes=taxonomy,price,promotion,bulk_ship,rating_and_review_reviews,rating_and_review_statistics,question_answer_statistics";
        RestTemplate restTemplate = new RestTemplate();

        String productJson = restTemplate.getForObject(url, String.class);
        JSONObject requestJsonObject = new JSONObject(productJson);
        JSONObject productJsonObject = requestJsonObject.getJSONObject("product");
        JSONObject itemJsonObject = productJsonObject.getJSONObject("item");
        JSONObject productDescriptionJsonObject = itemJsonObject.getJSONObject("product_description");

        return productDescriptionJsonObject.getString("title");
    }

    public ProductPricingInfo getPricingInfo(int productId) {
        Optional<ProductPricingInfoDO> productPricingInfoDO = productPricingDatabase.getProductPricingInfo(productId);
        ProductPricingInfo productPricingInfo = new ProductPricingInfo();
        if (productPricingInfoDO.isPresent()) {
            productPricingInfo.setId(productPricingInfoDO.get().getId());
            productPricingInfo.setCurrencyCode(productPricingInfoDO.get().getCurrencyCode());
            productPricingInfo.setPrice(productPricingInfoDO.get().getPrice());
        }
        return productPricingInfo;
    }

    public Product putProduct(int productId, Product product) {
        productPricingDatabase.putProductPricingInfo(productId, product.getProductPricingInfo());
        return product;
    }
}