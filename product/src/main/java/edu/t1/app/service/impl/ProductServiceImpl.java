package edu.t1.app.service.impl;

import edu.t1.app.enums.Operation;
import edu.t1.app.mapper.ProductDtoToEntityMapper;
import edu.t1.app.mapper.ProductEntityToDtoMapper;
import edu.t1.app.model.ProductEntity;
import edu.t1.app.model.ProductOperation;
import edu.t1.app.model.dto.ProductDto;
import edu.t1.app.repository.ProductRepository;
import edu.t1.app.service.ProductService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
@RequiredArgsConstructor
@Slf4j
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
        log.info("Product creted successfully");
        return productEntityToDtoMapper.apply(createdProduct);
    }

    @Override
    @Transactional
    public ProductDto update(ProductOperation productOperation) {
        Optional<ProductEntity> productOpt = productRepository.findById(productOperation.getProductId());
        ProductEntity updatedProductEntity = productOpt.orElseThrow(EntityNotFoundException::new);
        if (productOperation.getOperation().equals(Operation.DEPOSIT)) {
            updatedProductEntity.setBalance(updatedProductEntity.getBalance().add(productOperation.getAmount()));
        } else {
            if (productOperation.getOperation().equals(Operation.WITHDROW)) {
                updatedProductEntity.setBalance(updatedProductEntity.getBalance().subtract(productOperation.getAmount()));
            }
        }
        productRepository.save(updatedProductEntity);
        ProductDto updatedProductDto = productEntityToDtoMapper.apply(updatedProductEntity);
        log.info("Product updated successfully");
        return updatedProductDto;
    }

    @Override
    public void delete(Long id) {
        productRepository.deleteById(id);
    }
}
