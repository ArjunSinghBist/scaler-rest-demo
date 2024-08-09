package org.scaler.demo.project.service.impl;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Map;

import org.scaler.demo.project.dto.CategoryRequestDTO;
import org.scaler.demo.project.dto.ProductDTO;
import org.scaler.demo.project.dto.ProductRequestDTO;
import org.scaler.demo.project.exceptions.ItemNotFoundException;
import org.scaler.demo.project.model.Category;
import org.scaler.demo.project.model.Product;
import org.scaler.demo.project.repository.ICategoryRepository;
import org.scaler.demo.project.repository.IProductRepository;
import org.scaler.demo.project.service.IProductService;
import org.scaler.demo.project.utils.JsonUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements IProductService {
    private final IProductRepository productRepository;
    private final ICategoryRepository categoryRepository;

    public ProductServiceImpl(IProductRepository productRepository, ICategoryRepository categoryRepository) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
    }

    @Override
    public ProductDTO addNewProduct(ProductRequestDTO productRequestDTO) {
        CategoryRequestDTO categoryRequestDTO = productRequestDTO.categoryRequestDTO();

        // Create product
        Product product = Product.builder()
                                 .name(productRequestDTO.name())
                                 .description(productRequestDTO.description())
                                 .imageUrl(productRequestDTO.imageUrl())
                                 .build();
        product.setCreatedAt(LocalDateTime.now());
        product.setLastUpdatedAt(LocalDateTime.now());

        // check if category exists or not. If it does not exist add it
        Optional<Category> categoryOptional = categoryRepository.findByName(categoryRequestDTO.catName());

        if (categoryOptional.isEmpty()) {
            //Create Category
            Category category = Category.builder()
                                        .name(categoryRequestDTO.catName())
                                        .description(categoryRequestDTO.catDesc())
                                        .build();
            category.setCreatedAt(LocalDateTime.now());
            category.setLastUpdatedAt(LocalDateTime.now());

            product.setCategory(categoryRepository.save(category));
        }
        else {
            product.setCategory(categoryOptional.get());
        }

        // save product
        product = productRepository.save(product);

        return getProductById(product.getId());
    }

    // Retrieve product by Id
    @Override
    public ProductDTO getProductById(Long id) {
        Map<String, Object> resMap = productRepository.nativeClassBasedProductById(id);
        if(resMap.isEmpty()) {
            throw new ItemNotFoundException(String.format("Product with Id = %d not found!!", id));
        }

        return JsonUtils.convertMapToProjection(resMap, ProductDTO.class);
    }

    // Update the product
    @Override
    public ProductDTO updateProduct(Product product) {
        // check if product exists in db
        Optional<Product> productOptional = productRepository.findById(product.getId());

        if(productOptional.isEmpty()) {
            throw new ItemNotFoundException(String.format("Product with Id = %d not found!!", product.getId()));
        }

        productRepository.save(product);
        return getProductById(product.getId());
    }

    @Override
    public List<ProductDTO> getAllProducts() {
        List<Map<String, Object>> productsMap = productRepository.getAllProducts();
        List<ProductDTO> productDTOs = new ArrayList<>();

        for (Map<String, Object> productMap : productsMap) {
            productDTOs.add(JsonUtils.convertMapToProjection(productMap, ProductDTO.class));
        }

        return productDTOs;
    }
}
