package org.scaler.demo.project.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.scaler.demo.project.dto.ProductDTO;
import org.scaler.demo.project.dto.ProductRequestDTO;
import org.scaler.demo.project.exceptions.ItemNotFoundException;
import org.scaler.demo.project.model.Category;
import org.scaler.demo.project.model.Product;
import org.scaler.demo.project.service.IProductService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping("/products")
public class ProductController {
    private final ObjectMapper objectMapper;
    private IProductService productService;

    public ProductController(IProductService productService, @Qualifier("objectMapper") ObjectMapper objectMapper) {
        this.productService = productService;
        this.objectMapper = objectMapper;
    }

    //add new products
    @PostMapping()
    public ProductDTO addProduct(@RequestBody ProductRequestDTO product) {
        return productService.addNewProduct(product);
    }

    // get all products
    @GetMapping(produces = APPLICATION_JSON_VALUE)
    public List<ProductDTO> getAllProducts() {
        return productService.getAllProducts();
    }

    // get individual product
    @GetMapping("/{productId}")
    public ProductDTO getProduct(@PathVariable Long productId) {
        return productService.getProductById(productId);
    }

    // Update the product
    @PutMapping("/{productId}")
    public ProductDTO updateProduct(@PathVariable Long productId, @RequestBody ProductDTO prodDto) {
        // check if the id passed as parameter and in body are a match
        if (productId.equals(prodDto.Id())) {
            return productService.updateProduct(convertToProduct(prodDto));
        }

        throw new ItemNotFoundException("Product Id shared does not match the payload!");
    }

    private Product convertToProduct(ProductDTO prodDto) {

        Product product = Product.builder()
                                 .name(prodDto.name())
                                 .description(prodDto.description())
                                 .imageUrl(prodDto.imageUrl())
                                 .category(Category.builder()
                                                   .name(prodDto.category().catName())
                                                   .description(prodDto.category().catDesc())
                                                   .build()
                                          )
                                 .build();
        product.setId(prodDto.Id());
        product.setCreatedAt(LocalDateTime.parse(prodDto.createdAt()));
        product.setLastUpdatedAt(LocalDateTime.parse(prodDto.lastUpdatedAt()));
        return product;
    }
}
