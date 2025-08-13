package edu.t1.app.controller;

import edu.t1.app.model.PaymentRequest;
import edu.t1.app.model.ProductDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;


@RequestMapping("payments")
public interface PaymentController {
    @PostMapping
    ResponseEntity<ProductDto> payment(@RequestBody PaymentRequest paymentRequest);
}
