package edu.t1.app.service;

import edu.t1.app.model.ProductOperation;
import edu.t1.app.model.dto.ProductDto;

import java.util.List;

public interface ProductService {
    List<ProductDto> findByUserId(Long userId);

    ProductDto findById(Long productId);

    ProductDto create(ProductDto productDto);
    ProductDto update(ProductOperation productOperation);

    void delete(Long id);


}

