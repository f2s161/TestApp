package edu.t1.app.controller;

import edu.t1.app.model.dto.ProductDto;
import edu.t1.app.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ProductsControllerImpl implements ProductsController {
    private final ProductService productService;

    @Override
    public List<ProductDto> getProducts(Long userId) {
        return productService.findByUserId(userId);
    }

    @Override
    public ProductDto getProduct(Long productId) {
        return productService.findById(productId);
    }

    @Override
    public ProductDto create(ProductDto productDto) {
        return productService.create(productDto);
    }

    @Override
    public void delete(Long id) {
        productService.delete(id);
    }
}
