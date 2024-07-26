package org.scaler.demo.project.utils;

/**
 * This class is used to Convert Objects from database queries to DTO projections
 * */

import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.Map;

import com.mysql.cj.util.StringUtils;
import org.scaler.demo.project.configuration.ObjectMapperConfig;
import org.scaler.demo.project.exceptions.ItemNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class JsonUtils {

    private static final ObjectMapper objectMapper = ObjectMapperConfig.getObjectMapper();
    private static final Logger log = LoggerFactory.getLogger(JsonUtils.class);


    // Returning product projection. Better way would be to use a generic type
    public static <T> T convertMapToProjection(Map<?, ?> map, Class<T> refClass) {
        return objectMapper.convertValue(map, refClass);
    }

    // Check and return json node
    public static String JsonNodeToText(JsonNode jsonNode, String name) {
        JsonNode tempNode = jsonNode.path(name);
        return tempNode.isMissingNode() ? null : tempNode.asText().trim();
    }

    // return null if the string value contains null or is empty
    public static String sanitizeForNull(String var){
        if(StringUtils.isNullOrEmpty(var) || var.equalsIgnoreCase("null"))
            return null;

        return var;
    }

    // Check and throw ItemNotFoundException for null objects as we need to send the message
    // to the user. This is basically our version of Objects.requireNonNull
    public static <T> T requireNonNull(T obj, String message) throws JsonMappingException {
        if(obj == null)  {
            throw new JsonMappingException(message);
        }

        return obj;
    }
}
