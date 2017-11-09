package com.retail.model;

import java.util.List;

public class Response {

  private List<Product> productList;

  private String status;

  private Error error;

  public List<Product> getProductList() {
    return productList;
  }

  public void setProductList(List<Product> productList) {
    this.productList = productList;
  }

  public String getStatus() {
    return status;
  }

  public void setStatus(String status) {
    this.status = status;
  }

  public Error getError() {
    return error;
  }

  public void setError(Error error) {
    this.error = error;
  }
}
