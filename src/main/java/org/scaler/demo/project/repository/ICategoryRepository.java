package org.scaler.demo.project.repository;

import org.scaler.demo.project.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ICategoryRepository extends JpaRepository<Category, Long> {

    Optional<Category> findByName(String name);
}
