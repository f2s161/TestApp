package edu.t1.app.service;

import edu.t1.app.model.PaymentRequest;
import edu.t1.app.model.ProductDto;

public interface PaymentService {
    ProductDto processPayment(PaymentRequest paymentRequest);
}
