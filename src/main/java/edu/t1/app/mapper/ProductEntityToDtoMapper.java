package edu.t1.app.mapper;

import edu.t1.app.model.dto.ProductDto;
import edu.t1.app.model.ProductEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.function.Function;

@Component
@RequiredArgsConstructor
public class ProductEntityToDtoMapper implements Function<ProductEntity, ProductDto> {
    private final UserEntityToDtoMapper userEntityToDtoMapper;

    @Override
    public ProductDto apply(ProductEntity entity) {
        ProductDto dto = new ProductDto();
        dto.setId(entity.getId());
        dto.setBalance(entity.getBalance());
        dto.setAccountNumber(entity.getAccountNumber());
        dto.setProductType(entity.getProductType());
        dto.setUser(entity.getUser() != null ? userEntityToDtoMapper.apply(entity.getUser()) : null);
        return dto;
    }
}
