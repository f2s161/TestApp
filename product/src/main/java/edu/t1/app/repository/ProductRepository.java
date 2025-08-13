package edu.t1.app.repository;

import edu.t1.app.model.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<ProductEntity, Long> {
    List<ProductEntity> findByUserId(Long userId);

    void deleteById(Long id);
}
