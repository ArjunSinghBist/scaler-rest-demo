package org.scaler.demo.project.stubs;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.scaler.demo.project.dto.CategoryDTO;
import org.scaler.demo.project.dto.ProductDTO;
import org.scaler.demo.project.dto.ProductRequestDTO;
import org.scaler.demo.project.model.Category;
import org.scaler.demo.project.model.Product;
import org.scaler.demo.project.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

@Service
@Primary
public class ProductServiceStub implements IProductService {

    // create at in memory map
    Map<Long, Product> products = new HashMap<Long, Product>();

    private Long prodId = 0L;

    @Autowired
    private final ObjectMapper objectMapper;

    public ProductServiceStub(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }


    @Override
    public ProductDTO addNewProduct(ProductRequestDTO product) {
        products.put(++prodId, createProduct(product));
        return getProductById(prodId);
    }

    // convert product request dto to product
    private Product createProduct(ProductRequestDTO product) {
        Category category = Category.builder()
                                    .name(product.categoryRequestDTO().catName())
                                    .description(product.categoryRequestDTO().catDesc())
                                    .build();
        Product newProduct = Product.builder()
                                    .name(product.name())
                .description(product.description())
                .category(category)
                .imageUrl(product.imageUrl())
                .build();
        newProduct.setId(prodId);
        newProduct.setCreatedAt(LocalDateTime.now());
        newProduct.setLastUpdatedAt(LocalDateTime.now());
        return newProduct;
    }

    @Override
    public List<ProductDTO> getAllProducts() {
        return List.of();
    }

    @Override
    public ProductDTO getProductById(Long productId) {
        Optional<Product> productOptional = Optional.ofNullable(products.get(productId));
        Product product = productOptional.orElseThrow(() -> new RuntimeException("Product not found"));

        return new ProductDTO(product.getId()
        , product.getName()
        , product.getDescription()
                , product.getImageUrl()
                , new CategoryDTO(product.getCategory().getName(), product.getCategory().getDescription())
                , product.getCreatedAt().toString()
                , product.getLastUpdatedAt().toString()
                );
    }

    @Override
    public ProductDTO updateProduct(Product product) {
        products.put(product.getId(), product);
        return getProductById(product.getId());
    }
}
