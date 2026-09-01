package com.emanueltito.products_service.repository;

import com.emanueltito.products_service.model.Product;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    Optional<Product> findByCode(String code);

    List<Product> findByCodeIn(List<String> codes);

    boolean existsByCode(String code);
}
