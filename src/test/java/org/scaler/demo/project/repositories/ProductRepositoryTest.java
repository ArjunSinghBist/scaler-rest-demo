package org.scaler.demo.project.repositories;

import org.junit.jupiter.api.Test;
import org.scaler.demo.project.dto.projection.RProductProjection;
import org.scaler.demo.project.model.Product;
import org.scaler.demo.project.repository.IProductRepository;
import org.scaler.demo.project.utils.JsonUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Map;
import java.util.Optional;

@SpringBootTest
public class ProductRepositoryTest {

    private static final Logger logger = LoggerFactory.getLogger(ProductRepositoryTest.class);
    @Autowired
    private IProductRepository iProductRepository;

    @Test
    void testProductQueries() {
       Optional<Product> productOptional = null;

//       productOptional = iProductRepository.findById(101L);
//       Product product = productOptional.orElseThrow();
//       logger.debug("Product retrieved {}", product);
//       // Below line will work if FetchType for category is EAGER, else we get Lazy
//     // LazyInitialization exception
//       logger.debug("Retreiving category lazy loaded {}", product.getCategory());

//        productOptional = iProductRepository.getById(101L);
//        // Testing JPQL
//        logger.debug("Proudct retreived by id {}", productOptional.orElseThrow());

//        // Testing NativeQuery
//        productOptional = iProductRepository.nativeGetById(101L);
//        logger.debug("Native query executed for product {}", productOptional);

        // Using Interface based closed projection
//        Optional<IProductProjection> productProjection = iProductRepository.nativeGetProductById(101L);
//        logger.debug("Retrieved product projection {}", productProjection.orElseThrow());
//        logger.debug("Name => " + productProjection.get().getName().get() + ", Description => " + productProjection.get().getDescription().get());

        // Class based DTO projections will not work with Native Query
        Map<String, Object> productDTO = iProductRepository.nativeClassBasedProductById(101L);
        RProductProjection prodcutProjection = JsonUtils.convertMapToProjection(productDTO, RProductProjection.class);
        logger.debug("Name => " + prodcutProjection.name() + ". description => " + prodcutProjection.description());
    }
}
