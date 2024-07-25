package org.scaler.demo.project.utils;

/**
 * This class is used to Convert Objects from database queries to DTO projections
 * */

import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.Map;
import org.scaler.demo.project.configuration.ObjectMapperConfig;

public class JsonUtils {

    private static final ObjectMapper objectMapper = ObjectMapperConfig.getObjectMapper();


    // Returning product projection. Better way would be to use a generic type
    public static <T> T convertMapToProjection(Map<?, ?> map, Class<T> refClass) {
        return objectMapper.convertValue(map, refClass);
    }
}
