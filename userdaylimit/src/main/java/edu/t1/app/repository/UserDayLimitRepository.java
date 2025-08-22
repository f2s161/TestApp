package edu.t1.app.repository;

import edu.t1.app.model.UserDayLimitEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserDayLimitRepository extends JpaRepository<UserDayLimitEntity, Long> {
    Optional<UserDayLimitEntity> findByUsername(String username);
}
