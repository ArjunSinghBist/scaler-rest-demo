package org.scaler.demo.project.controller;


import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.scaler.demo.project.dto.CategoryRequestDTO;
import org.scaler.demo.project.dto.ProductDTO;
import org.scaler.demo.project.dto.ProductRequestDTO;
import org.scaler.demo.project.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;

@SpringBootTest
public class ProductControllerFlowTest {

    @Autowired
    private ProductController productController;
    private ProductDTO fetchedProduct;


    @Test
    @DisplayName("ProductController flow test")
    public void Test_ProductControllerFlow() {

        // Create Product
        ProductRequestDTO productRequestDTO = new ProductRequestDTO("Iphone 6"
                , "Small Iphone"
                , "https://apple.com/in/6s.img"
                , new CategoryRequestDTO("Old Iphone", "Expired Iphones")
        );

        productController.addProduct(productRequestDTO);

        // Fetch Product
        ProductDTO fetchedProduct = productController.getProduct(1L);

        // update product
        ProductDTO updatedProduct = new ProductDTO(
                fetchedProduct.Id()
                , "Updated Iphone name"
                , "Small updated Iphone"
                , fetchedProduct.imageUrl()
                , fetchedProduct.category()
                , fetchedProduct.createdAt()
                , LocalDateTime.now().toString()
        );

        productController.updateProduct(fetchedProduct.Id(), updatedProduct);

        // fetch updated product
        ProductDTO fetchUpdatedProduct = productController.getProduct(1L);

        // assert
        assert(productRequestDTO.name().equalsIgnoreCase(fetchedProduct.name()));
        assert(productRequestDTO.description().equalsIgnoreCase(fetchedProduct.description()));
        assert(updatedProduct.name().equalsIgnoreCase(fetchUpdatedProduct.name()));
        assert(updatedProduct.description().equalsIgnoreCase(fetchUpdatedProduct.description()));
        assert(fetchedProduct.category().catName().equals(fetchUpdatedProduct.category().catName()));
    }
}
