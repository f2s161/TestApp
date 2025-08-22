package edu.t1.app.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@Entity
@Table(name = "user_day_limit")
@AllArgsConstructor
@NoArgsConstructor
public class UserDayLimitEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_day_limit_gen")
    @SequenceGenerator(name="user_day_limit_gen", sequenceName="user_day_limit_id_seq", allocationSize = 1)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "current_day_limit")
    private BigDecimal currentUserDayLimit;
    @Column(name = "default_day_limit")
    private BigDecimal defaultUserDayLimit = new BigDecimal("10000");
    private String username;
}
