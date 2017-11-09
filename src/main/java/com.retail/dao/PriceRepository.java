package com.retail.dao;

import com.retail.model.Price;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface PriceRepository extends MongoRepository<Price, String> {
  Price findByProductId(Long productId);
}
