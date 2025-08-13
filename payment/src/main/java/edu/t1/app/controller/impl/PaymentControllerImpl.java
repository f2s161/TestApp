package edu.t1.app.controller.impl;

import edu.t1.app.controller.PaymentController;
import edu.t1.app.model.PaymentRequest;
import edu.t1.app.model.ProductDto;
import edu.t1.app.service.PaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class PaymentControllerImpl implements PaymentController {
    private final PaymentService paymentService;

    @Override
    public ResponseEntity<ProductDto> payment(PaymentRequest paymentRequest) {
        return ResponseEntity.ok(paymentService.processPayment(paymentRequest));
    }
}
