package edu.t1.app.controller;

import edu.t1.app.model.ProductDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@RequestMapping("products")
public interface ProductController {
    @GetMapping
    ResponseEntity<List<ProductDto>> getProducts(@RequestParam("userId") Long userId);
}
