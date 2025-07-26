package edu.t1.app.service.impl;

import edu.t1.app.mapper.ProductDtoToEntityMapper;
import edu.t1.app.mapper.ProductEntityToDtoMapper;
import edu.t1.app.model.ProductEntity;
import edu.t1.app.model.dto.ProductDto;
import edu.t1.app.repository.ProductRepository;
import edu.t1.app.service.ProductService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;
    private final ProductEntityToDtoMapper productEntityToDtoMapper;
    private final ProductDtoToEntityMapper productDtoToEntityMapper;

    @Override
    public List<ProductDto> findByUserId(Long userId) {
        return productRepository.findByUserId(userId).stream().map(productEntityToDtoMapper).toList();
    }

    @Override
    public ProductDto findById(Long productId) {
        return productEntityToDtoMapper.apply(productRepository.findById(productId).orElseThrow(EntityNotFoundException::new));
    }

    @Override
    public ProductDto create(ProductDto productDto) {
        ProductEntity createdProduct = productRepository.save(productDtoToEntityMapper.apply(productDto));
        System.out.println("Product creted successfully");
        return productEntityToDtoMapper.apply(createdProduct);
    }

    @Override
    public void delete(Long id) {
        productRepository.deleteById(id);
    }
}
