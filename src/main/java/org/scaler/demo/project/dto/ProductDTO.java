package org.scaler.demo.project.dto;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

@JsonDeserialize(using = ProductDeserializer.class)
public record ProductDTO(Long Id, String name, String description, CategoryDTO category, String createdAt,
                         String lastUpdatedAt) {

}
