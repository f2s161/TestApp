package edu.t1.app.controller;

import edu.t1.app.model.ProductOperation;
import edu.t1.app.model.dto.ProductDto;
import edu.t1.app.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ProductsControllerImpl implements ProductsController {
    private final ProductService productService;

    @Override
    public ResponseEntity<List<ProductDto>> getProducts(Long userId) {
        return ResponseEntity.ok(productService.findByUserId(userId));
    }

    @Override
    public ResponseEntity<ProductDto> getProduct(Long productId) {
        return ResponseEntity.ok(productService.findById(productId));
    }

    @Override
    public ResponseEntity<ProductDto> create(ProductDto productDto) {
        return ResponseEntity.ok(productService.create(productDto));
    }

    @Override
    public ResponseEntity<Void> delete(Long id) {
        productService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @Override
    public ResponseEntity<ProductDto> updateProduct(ProductOperation productOperation) {
        return ResponseEntity.ok(productService.update(productOperation));
    }
}
