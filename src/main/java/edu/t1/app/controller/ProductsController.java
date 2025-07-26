package edu.t1.app.controller;

import edu.t1.app.model.dto.ProductDto;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("products")
public interface ProductsController {
    @GetMapping
    List<ProductDto> getProducts(@RequestParam("userId") Long userId);

    @GetMapping("/{id}")
    ProductDto getProduct(@PathVariable("id") Long id);

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    ProductDto create(@RequestBody ProductDto productDto);

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void delete(@PathVariable("id") Long id);
}

