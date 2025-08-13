package edu.t1.app.controller.impl;
import edu.t1.app.controller.ProductController;
import edu.t1.app.model.ProductDto;
import edu.t1.app.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ProductsControllerImpl implements ProductController {
    private final ProductService productService;

    @Override
    public ResponseEntity<List<ProductDto>> getProducts(Long userId) {
        return ResponseEntity.ok(productService.getUserProducts(userId));
    }


}
