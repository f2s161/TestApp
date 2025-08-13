package edu.t1.app.service;

import edu.t1.app.model.ProductDto;
import edu.t1.app.model.ProductOperation;

import java.util.List;

public interface ProductService {
    List<ProductDto> getUserProducts(Long userId);

    ProductDto updateProduct(ProductOperation productOperation);
}
