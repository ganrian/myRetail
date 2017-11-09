package com.retail.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.CollectionType;
import com.fasterxml.jackson.databind.type.TypeFactory;
import com.retail.model.Product;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class ProductDetailGenerator {

  private int id;

  public ProductDetailGenerator(int id) {
    this.id = id;
  }

  public Product getMockProduct() {
    try {
      ObjectMapper mapper = new ObjectMapper();
      ClassLoader classLoader = getClass().getClassLoader();
      File file = new File(classLoader.getResource("mockData.json").getFile().replace("%23","#"));

      TypeFactory typeFactory = mapper.getTypeFactory();
      CollectionType collectionType = typeFactory.constructCollectionType(List.class, Product.class);
      List<Product> productList =  mapper.readValue(file, collectionType);
      for(Product product : productList) {
        if(product.getId() == id) {
          return product;
        }
      }
    } catch (IOException e) {
      //Returning Empty data
      return null;
    }

    return null;
  }
}
