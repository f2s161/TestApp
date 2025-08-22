package edu.t1.app.model;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class UserDayLimitWithdrow {
    private String username;
    private BigDecimal withdraw;
}
