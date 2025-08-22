package edu.t1.app.model;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class UserDayLimitRequest {
    private BigDecimal defaultUserDayLimit;
    private String username;
}
