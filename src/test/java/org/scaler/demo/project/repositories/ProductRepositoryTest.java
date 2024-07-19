package org.scaler.demo.project.repositories;

import org.junit.jupiter.api.Test;
import org.scaler.demo.project.model.Product;
import org.scaler.demo.project.repository.IProductRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

@SpringBootTest
public class ProductRepositoryTest {

    private static final Logger logger = LoggerFactory.getLogger(ProductRepositoryTest.class);
    @Autowired
    private IProductRepository iProductRepository;

    @Test
    void testProductQueries() {
       Optional<Product> productOptional = iProductRepository.findById(101L);
       logger.debug("Product retrieved {}", productOptional.orElseThrow());
    }
}
