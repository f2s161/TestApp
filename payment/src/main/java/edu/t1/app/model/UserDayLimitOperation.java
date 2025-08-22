package edu.t1.app.model;

import edu.t1.app.enums.Operation;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class UserDayLimitOperation {
    private String username;
    private BigDecimal amount;
    private Operation operation;
}
