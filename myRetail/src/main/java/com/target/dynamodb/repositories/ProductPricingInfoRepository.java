package com.target.dynamodb.repositories;

import com.target.dynamodb.model.ProductPricingInfoDO;
import org.socialsignin.spring.data.dynamodb.repository.EnableScan;
import org.springframework.data.repository.CrudRepository;

@EnableScan
public interface ProductPricingInfoRepository extends CrudRepository<ProductPricingInfoDO, Integer> {
}
