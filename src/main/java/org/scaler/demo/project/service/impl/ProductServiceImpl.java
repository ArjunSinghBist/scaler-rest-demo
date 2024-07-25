package org.scaler.demo.project.service.impl;

import java.util.ArrayList;
import java.util.Map;
import org.scaler.demo.project.dto.ProductDTO;
import org.scaler.demo.project.model.Product;
import org.scaler.demo.project.repository.IProductRepository;
import org.scaler.demo.project.service.ProductService;
import org.scaler.demo.project.utils.JsonUtils;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {
    private final IProductRepository productRepository;

    public ProductServiceImpl(IProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public Product addNewProduct(Product product) {
        productRepository.save(product);
        return null;
    }

    @Override
    public List<ProductDTO> getAllProducts() {
        List<Map<String, Object>> productsMap = productRepository.getAllProducts();
        List<ProductDTO> productDTOs = new ArrayList<>();

        for(Map<String, Object> productMap : productsMap) {
            productDTOs.add(JsonUtils.convertMapToProjection(productMap, ProductDTO.class));
        }

        return productDTOs;
    }
}
