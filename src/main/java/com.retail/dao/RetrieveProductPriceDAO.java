package com.retail.dao;

import com.mongodb.*;
import com.retail.model.Price;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessResourceFailureException;
import org.springframework.stereotype.Repository;

@Repository
public class RetrieveProductPriceDAO {

  @Autowired
  private PriceRepository priceRepository;

  public Price getProductPrice(int id) {
    Price responsePrice = null;
    try {
      priceRepository.findByProductId(Long.valueOf(id));
    } catch (MongoException | DataAccessResourceFailureException mongoException) {
      //Sending Mock Data for the sake this exercise
      responsePrice = new Price();
      responsePrice.setProductId(Long.valueOf(id));
      responsePrice.setUnitPrice(99.99);
    } catch (Exception exception) {
      return responsePrice;
    }
    return responsePrice;
  }
}
