package edu.t1.app.model;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class PaymentResponse {
    private Long userId;
    private BigDecimal remainingAmount;
}
