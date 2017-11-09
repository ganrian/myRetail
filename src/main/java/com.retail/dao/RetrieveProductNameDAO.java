package com.retail.dao;

import com.retail.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@Repository
public class RetrieveProductNameDAO {

  @Autowired
  private RestTemplate restTemplate;

  @Value("${product.name.url}")
  private String url;

  public Product getProductDetails(int id) {
    Product responseProduct = null;
    HttpEntity entity = new HttpEntity(getHeaders());
    try {
      Map<String, String> productId = new HashMap<>();
      productId.put("id", String.valueOf(id));
      ResponseEntity<Product> productNameResponseEntity = restTemplate.exchange(url, HttpMethod.GET, entity, Product.class, productId);
      if (productNameResponseEntity != null) {
        responseProduct = productNameResponseEntity.getBody();
      }
    } catch (Exception exception) {
      return responseProduct;
    }

    return responseProduct;
  }

  private HttpHeaders getHeaders() {
    HttpHeaders headers = new HttpHeaders();
    headers.set("Accept", "application/json");
    return headers;
  }
}
