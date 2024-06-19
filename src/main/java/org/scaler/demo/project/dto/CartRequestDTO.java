package org.scaler.demo.project.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.scaler.demo.project.configuration.deserializer.DateDeserializer;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class CartRequestDTO {

    private long userId;

    @JsonDeserialize(using = DateDeserializer.class)
    private LocalDateTime date;
    private List<ProductDTO> products;
}
