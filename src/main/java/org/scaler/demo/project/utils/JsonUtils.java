package org.scaler.demo.project.utils;

/**
 * This class is used to Convert Objects from database queries to DTO projections
 * */

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import jakarta.annotation.PostConstruct;
import lombok.Getter;
import java.util.Map;
import org.scaler.demo.project.dto.projection.RProductProjection;

public class JsonUtils {
    
    @Getter
    private static final ObjectMapper objectMapper = new ObjectMapper();

    @PostConstruct
    public void setUpObjectMapper() {
        objectMapper.registerModule(new JavaTimeModule());
        objectMapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        objectMapper.enable(MapperFeature.ACCEPT_CASE_INSENSITIVE_PROPERTIES);
    }

    // Returning product projection. Better way would be to use a generic type
    public static <T> T convertMapToProjection(Map<?, ?> map, Class<T> refClass) {
        return objectMapper.convertValue(map, refClass);
    }
}
