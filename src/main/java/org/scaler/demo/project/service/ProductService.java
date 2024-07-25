package org.scaler.demo.project.service;

import org.scaler.demo.project.dto.ProductDTO;
import org.scaler.demo.project.model.Product;

import java.util.List;

public interface ProductService {
    Product addNewProduct(Product product);

    List<ProductDTO> getAllProducts();
}
