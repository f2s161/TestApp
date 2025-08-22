package edu.t1.app.controller;

import edu.t1.app.model.ProductOperation;
import edu.t1.app.model.dto.ProductDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("products")
public interface ProductsController {
    @GetMapping
    ResponseEntity<List<ProductDto>> getProducts(@RequestParam("userId") Long userId);

    @GetMapping("/{id}")
    ResponseEntity<ProductDto> getProduct(@PathVariable("id") Long id);

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    ResponseEntity<ProductDto> create(@RequestBody ProductDto productDto);

    @PatchMapping
    @ResponseStatus(HttpStatus.OK)
    ResponseEntity<ProductDto> updateProduct(@RequestBody ProductOperation productOperation);

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    ResponseEntity delete(@PathVariable("id") Long id);
}

