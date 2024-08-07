package org.scaler.demo.project.service;

import org.scaler.demo.project.dto.ProductDTO;
import org.scaler.demo.project.dto.ProductRequestDTO;

import java.util.List;

public interface IProductService {
    ProductDTO addNewProduct(ProductRequestDTO product);

    List<ProductDTO> getAllProducts();

    ProductDTO getProductById(Long productId);
}
