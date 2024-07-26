package org.scaler.demo.project.dto;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import org.scaler.demo.project.utils.deserializer.ProductDTODeserializer;

@JsonDeserialize(using = ProductDTODeserializer.class)
public record ProductDTO(Long Id, String name, String description, String imageUrl, CategoryDTO category, String createdAt,
                         String lastUpdatedAt) {

}
