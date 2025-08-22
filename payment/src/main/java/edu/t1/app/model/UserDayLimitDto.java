package edu.t1.app.model;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class UserDayLimitDto {
    private Long id;
    private BigDecimal currentDayLimit;
    private BigDecimal defaultDayLimit;
    private String username;
}
