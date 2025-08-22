package edu.t1.app.service.impl;

import edu.t1.app.model.ProductDto;
import edu.t1.app.model.ProductOperation;
import edu.t1.app.service.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.util.List;

@Service
@Slf4j
public class ProductServiceImpl implements ProductService {
    @Autowired
    @Qualifier("productRestClient")
    private RestClient productRestClient;

    @Override
    public List<ProductDto> getUserProducts(Long userId) {
        return productRestClient.get().uri(uriBuilder -> uriBuilder.path("products")
                        .queryParam("userId", userId).build())
                .retrieve().toEntity(new ParameterizedTypeReference<List<ProductDto>>() {
                }).getBody();
    }

    @Override
    public ProductDto updateProduct(ProductOperation productOperation) {
        return productRestClient.patch().uri(uriBuilder -> uriBuilder.path("products").build())
                .body(productOperation)
                .retrieve().toEntity(new ParameterizedTypeReference<ProductDto>() {
                }).getBody();
    }
}
