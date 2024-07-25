package org.scaler.demo.project.controller;

import org.scaler.demo.project.dto.ProductDTO;
import org.scaler.demo.project.model.Product;
import org.scaler.demo.project.service.ProductService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping("/products")
public class ProductController {
    private ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    //add new products
    @PostMapping()
    public Product addProduct(@RequestBody Product product) {
        return productService.addNewProduct(product);
    }

    // get all products
    @GetMapping(produces = APPLICATION_JSON_VALUE)
    public List<ProductDTO> getAllProducts() {
        return productService.getAllProducts();
    }
}
