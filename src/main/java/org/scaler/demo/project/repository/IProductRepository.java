package org.scaler.demo.project.repository;

import java.util.List;
import java.util.Map;

import org.scaler.demo.project.model.Product;
import org.scaler.demo.project.model.projection.IProductProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface IProductRepository extends JpaRepository<Product, Long> {

    @Override
    Optional<Product> findById(Long aLong);

    // JPQL
    @Query(value = "Select p from products p where p.Id = ?1")
    Optional<Product> getById(long productId);

    // Native query
    @Query(value = "SELECT * FROM products WHERE Id = :productId", nativeQuery = true)
    Optional<Product> nativeGetById(@Param("productId") long prodId);

    // Return a closed Interface Projection
    @Query(value = "SELECT name, description FROM products WHERE Id = :productId", nativeQuery = true)
    Optional<IProductProjection> nativeGetProductById(@Param("productId") long prodId);

    // Return a Class (Record) based DTO projection
    @Query(value = "SELECT p.name, p.description, p.id FROM products p WHERE Id = :productId", nativeQuery = true)
    Map<String, Object> nativeClassBasedProductById(@Param("productId") long prodId);

    @Query(value = "SELECT p.id, p.name, p.description, p.created_at as createdAt, p.last_updated_at as lastUpdatedAt, c.name as catName, c.description as catDesc FROM products p JOIN categories c ON p.category_id=c.id", nativeQuery = true)
    List<Map<String, Object>> getAllProducts();
}