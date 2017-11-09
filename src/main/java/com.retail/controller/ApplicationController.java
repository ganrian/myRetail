package com.retail.controller;

import com.retail.model.Product;
import com.retail.model.Response;
import com.retail.service.ApplicationService;
import com.retail.utils.ProductDetailGenerator;
import io.reactivex.Observable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.async.DeferredResult;

@RestController
@SpringBootApplication
@ComponentScan("com.retail")
@RequestMapping("/retail")
@EnableMongoRepositories("com.retail")
public class ApplicationController{

  @Autowired
  private ApplicationService service;

  @RequestMapping(value = "/product/getDetails", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
  public DeferredResult<Response> getProductDetails(@RequestParam (value = "id") int id) {
    DeferredResult<Response> deferredResult = new DeferredResult<>();
    Observable<Response> responseObservable = service.getProduct(id);
    responseObservable.subscribe(deferredResult::setResult, deferredResult::setErrorResult);
    return deferredResult;
  }

  @RequestMapping(value = "/product/getProductName", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
  public Product getProductName(@RequestParam (value = "id") int id) {
    ProductDetailGenerator dataGenerator = new ProductDetailGenerator(id);
    return dataGenerator.getMockProduct();
  }

  public static void main(String[] args) {
    SpringApplication.run(ApplicationController.class, args);
  }
}
