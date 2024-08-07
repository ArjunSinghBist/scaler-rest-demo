package org.scaler.demo.project.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.scaler.demo.project.dto.CategoryDTO;
import org.scaler.demo.project.dto.ProductDTO;
import org.scaler.demo.project.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(ProductController.class)
public class ProductControllerMVCTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    @MockBean
    private IProductService productService;

    @Test
    @DisplayName("Get list of products with valid response")
    public void Test_getAllProducts_ValidResponse() throws Exception {
        // Arrange
        CategoryDTO categoryDTO = new CategoryDTO("Iphone LC", "low cost Iphone");
        ProductDTO productDTO1 = new ProductDTO(101L
                , "Iphone 6"
                , "low cast Iphone"
                , "https://apple.com/in/iphone6.img"
                , categoryDTO
                , null
                , null);

        CategoryDTO categoryDTO1 = new CategoryDTO("Android Exp", "Expensive Android device");
        ProductDTO productDTO2 = new ProductDTO(110L
                , "Samsung S23"
                , "Samsung Phone expensive"
                , "https://samsung.com/in/s23.img"
                , categoryDTO1
                , null
                , null);

        List<ProductDTO> productDTOList = new ArrayList<>();
        productDTOList.add(productDTO1);
        productDTOList.add(productDTO2);

        when(productService.getAllProducts()).thenReturn(productDTOList);

        //object -> JSON -> List
        mockMvc.perform(get("/products"))
               .andExpect(status().isOk())
               .andExpect(content().string(objectMapper.writeValueAsString(productDTOList)));

        // matching individual parameter value
        mockMvc.perform(get("/products"))
                .andExpect(status().isOk())
                .andExpect(content().string(objectMapper.writeValueAsString(productDTOList)))
                .andExpect(jsonPath("$.length()").value(2))
                .andExpect(jsonPath("$[0].name").value("Iphone 6"))
                .andExpect(jsonPath("$[1].name").value("Samsung S23"))
                .andExpect(jsonPath("$[0].category.catName").value("Iphone LC"));
    }
}
