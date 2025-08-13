package edu.t1.app.service.impl;

import edu.t1.app.exception.NotEnoughFundsException;
import edu.t1.app.model.PaymentRequest;
import edu.t1.app.model.ProductDto;
import edu.t1.app.model.ProductOperation;
import edu.t1.app.service.PaymentService;
import edu.t1.app.service.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class PaymentServiceImpl implements PaymentService {
    private final ProductService productService;

    @Override
    public ProductDto processPayment(PaymentRequest paymentRequest) {
        ProductDto productDto = null;
        List<ProductDto> productDtoList = productService.getUserProducts(paymentRequest.getUserId());
        Optional<ProductDto> productDtoOptional = productDtoList.stream().filter(p -> p.getBalance()
                .compareTo(paymentRequest.getAmount()) > 0).findFirst();
        if (productDtoOptional.isPresent()) {
            ProductOperation productOperation = new ProductOperation();
            productOperation.setProductId(productDtoOptional.get().getId());
            productOperation.setAmount(paymentRequest.getAmount());
            productOperation.setOperation(paymentRequest.getOperation());
            productDto = productService.updateProduct(productOperation);
        } else {
            log.error("У пользователся не достаточно средств: {}", paymentRequest.getUserId());
            throw new NotEnoughFundsException("Не достаточно средств");
        }
        return productDto;
    }
}
