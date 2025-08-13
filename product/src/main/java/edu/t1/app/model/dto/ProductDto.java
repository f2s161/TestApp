package edu.t1.app.model.dto;

import edu.t1.app.enums.ProductType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductDto {
    private Long id;

    private String accountNumber;

    private BigDecimal balance;

    private ProductType productType;

    private UserDto user;
}
