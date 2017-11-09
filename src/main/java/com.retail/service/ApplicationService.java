package com.retail.service;

import com.retail.dao.RetrieveProductPriceDAO;
import com.retail.dao.RetrieveProductNameDAO;
import com.retail.model.Error;
import com.retail.model.Price;
import com.retail.model.Product;
import com.retail.model.Response;
import com.retail.utils.Constants;
import io.reactivex.Observable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ApplicationService {

  @Autowired
  private RetrieveProductPriceDAO getPriceDao;

  @Autowired
  private RetrieveProductNameDAO getProductName;

  public Observable<Response> getProduct(int id) {
    Response response = new Response();
    List<Product> productList = null;
    Product product = getProductName.getProductDetails(id);

    if(product != null) {
      productList = new ArrayList<>();
      productList.add(product);
      Price price = getPriceDao.getProductPrice(id);
      response.setProductList(productList);
      if(price != null) {
        product.setPrice(price.getUnitPrice());
        response.setError(null);
        response.setStatus(Constants.SUCCESS);
      } else {
        setErrorObject(response,HttpStatus.NOT_FOUND, Constants.PRICE_NOT_AVAILABLE);
      }
    } else {
      setErrorObject(response,HttpStatus.NOT_FOUND, Constants.PRODUCT_NOT_FOUND);
    }
    return Observable.just(response);
  }

  private void setErrorObject(Response response, HttpStatus status, String message) {
      Error error = new Error();
      error.setCode(status.toString());
      error.setMessage(message);
      response.setError(error);
      response.setStatus(Constants.FAILURE);
  }
}
