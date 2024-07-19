package org.scaler.demo.project.repository;

import org.scaler.demo.project.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface IProductRepository extends JpaRepository<Product, Long> {

    @Override
    Optional<Product> findById(Long aLong);
}
