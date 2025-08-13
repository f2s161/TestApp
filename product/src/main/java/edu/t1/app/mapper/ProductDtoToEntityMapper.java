package edu.t1.app.mapper;

import edu.t1.app.model.dto.ProductDto;
import edu.t1.app.model.ProductEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.function.Function;

@Component
@RequiredArgsConstructor
public class ProductDtoToEntityMapper implements Function<ProductDto, ProductEntity> {
    private final UserDtoToEntityMapper userDtoToEntityMapper;

    @Override
    public ProductEntity apply(ProductDto productDto) {
        ProductEntity entity = new ProductEntity();
        entity.setId(productDto.getId());
        entity.setBalance(productDto.getBalance());
        entity.setAccountNumber(productDto.getAccountNumber());
        entity.setProductType(productDto.getProductType());
        entity.setUser(productDto.getUser() != null ? userDtoToEntityMapper.apply(productDto.getUser()) : null);
        return entity;
    }
}
