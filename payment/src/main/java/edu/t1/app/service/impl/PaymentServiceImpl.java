package edu.t1.app.service.impl;

import edu.t1.app.enums.Operation;
import edu.t1.app.exception.NotEnoughFundsException;
import edu.t1.app.exception.UserDayLimitUpdateException;
import edu.t1.app.model.*;
import edu.t1.app.service.PaymentService;
import edu.t1.app.service.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class PaymentServiceImpl implements PaymentService {
    @Autowired
    private ProductService productService;
    @Autowired
    @Qualifier("usersDayLimitRestClient")
    private RestClient usersDayLimitRestClient;

    @Override
    public ProductDto processPayment(PaymentRequest paymentRequest) {
        ProductDto productDto = null;
        List<ProductDto> productDtoList = productService.getUserProducts(paymentRequest.getUserId());
        Optional<ProductDto> productDtoOptional = productDtoList.stream().filter(p -> p.getBalance()
                .compareTo(paymentRequest.getAmount()) >= 0).findFirst();
        if (productDtoOptional.isEmpty()) {
            log.error("У пользователя недостаточно средств: {}", paymentRequest.getUserId());
            throw new NotEnoughFundsException("Недостаточно средств");
        }
        if (paymentRequest.getUserId() <= 100) {
            //Уменьшение дневного лимита пользователя
            UserDayLimitOperation userDayLimitOperation = getUserDayLimitOperation(productDtoOptional.get().getUser().getUsername(),
                    paymentRequest.getAmount(), Operation.WITHDROW);
            changeLimit(userDayLimitOperation);
        }
        try {
            ProductOperation productOperation = new ProductOperation();
            productOperation.setProductId(productDtoOptional.get().getId());
            productOperation.setAmount(paymentRequest.getAmount());
            productOperation.setOperation(paymentRequest.getOperation());
            productDto = productService.updateProduct(productOperation);
            throw new RuntimeException();
        } catch (Exception ex) {
            if (paymentRequest.getUserId() <= 100) {
                //Увеличение дневного лимита пользователя
                UserDayLimitOperation userDayLimitOperation = getUserDayLimitOperation(productDtoOptional.get().getUser().getUsername(),
                        paymentRequest.getAmount(), Operation.DEPOSIT);
                changeLimit(userDayLimitOperation);
            }
            log.error("Ошибка проведения платежа: {}", ex.getMessage());
        }
        return productDto;
    }

    private UserDayLimitOperation getUserDayLimitOperation(String username, BigDecimal amount, Operation operation) {
        UserDayLimitOperation userDayLimitOperation = new UserDayLimitOperation();
        userDayLimitOperation.setUsername(username);
        userDayLimitOperation.setAmount(amount);
        userDayLimitOperation.setOperation(operation);
        return userDayLimitOperation;
    }

    private void changeLimit(UserDayLimitOperation userDayLimitOperation) {
        ResponseEntity<UserDayLimitDto> response = usersDayLimitRestClient.post().uri(uriBuilder -> uriBuilder
                        .pathSegment("v1", "user-day-limits", "change-limit").build())
                .body(userDayLimitOperation)
                .retrieve()
                .onStatus(HttpStatusCode::is4xxClientError, (req, res) -> {
                    log.error("Ошибка обновления дневного лимита пользователя: " + userDayLimitOperation.getUsername());
                    throw new UserDayLimitUpdateException("Ошибка обновления дневного лимита пользователя: " + userDayLimitOperation.getUsername());
                })
                .toEntity(new ParameterizedTypeReference<>() {
                });
        if (!response.getStatusCode().is2xxSuccessful()) {
            throw new UserDayLimitUpdateException("Ошибка обновления дневного лимита пользователя");
        }
    }
}
