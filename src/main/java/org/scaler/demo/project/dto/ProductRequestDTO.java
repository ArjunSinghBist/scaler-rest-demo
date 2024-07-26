package org.scaler.demo.project.dto;


import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import org.scaler.demo.project.utils.deserializer.ProductRequestDTODeserializer;

@JsonDeserialize(using = ProductRequestDTODeserializer.class)
public record ProductRequestDTO(String name, String description, String imageUrl, CategoryRequestDTO categoryRequestDTO) {
}
