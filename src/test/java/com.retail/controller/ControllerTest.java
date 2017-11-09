package com.retail.controller;

import com.retail.configuration.TestSpringConfiguration;
import com.retail.dao.PriceRepository;
import com.retail.model.Price;
import com.retail.model.Product;
import io.restassured.RestAssured;
import org.junit.Before;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.context.WebApplicationContext;

import java.util.HashMap;
import java.util.Map;

@ActiveProfiles("unit-test")
@ContextConfiguration(classes = TestSpringConfiguration.class)
@WebAppConfiguration
public class ControllerTest {

  @Autowired
  private WebApplicationContext context;

  @Mock
  private RestTemplate restTemplate;

  @Mock
  private PriceRepository priceRepository;

  @Before
  public void setUp() {
    MockitoAnnotations.initMocks(this);
    setUpData();
  }

  public void getProductDetails() {
    RestAssured.given().when().get("/retail/product/getDetails?id=15117729").then().statusCode(200);

  }
  private void setUpData() {
    HttpEntity entity = new HttpEntity(getHeaders());
    Map<String, String> productId = new HashMap<>();
    productId.put("id", String.valueOf(123));

    Product product = new Product();
    product.setId(123);
    product.setName("Test Product");
    product.setDescription("Test production description");
    product.setPrice(99.99);
    ResponseEntity<Product> responseEntity = new ResponseEntity<>(product, HttpStatus.OK);

    Long id = new Long(123);
    Price priceObject = new Price();
    priceObject.setProductId(id);
    priceObject.setUnitPrice(99.99);

    Mockito.doReturn(responseEntity).when(restTemplate).exchange("/retail/product/getDetails?id=123", HttpMethod.GET, entity, Product.class, productId);
    Mockito.doReturn(priceObject).when(priceRepository).findByProductId(id);
  }

  private HttpHeaders getHeaders() {
    HttpHeaders headers = new HttpHeaders();
    headers.set("Accept", "application/json");
    return headers;
  }
}