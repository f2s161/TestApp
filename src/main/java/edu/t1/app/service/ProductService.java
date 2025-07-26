package edu.t1.app.service;

import edu.t1.app.model.dto.ProductDto;

import java.util.List;

public interface ProductService {
    List<ProductDto> findByUserId(Long userId);

    ProductDto findById(Long productId);

    ProductDto create(ProductDto productDto);

    void delete(Long id);
}

