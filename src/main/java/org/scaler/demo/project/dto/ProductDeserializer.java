package org.scaler.demo.project.dto;

import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import org.scaler.demo.project.utils.deserializer.DateToString;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ProductDeserializer extends StdDeserializer<ProductDTO> {

    public ProductDeserializer() {
        this(null);
    }

    public ProductDeserializer(Class<?> vc) {
        super(vc);
    }

    @Override
    public ProductDTO deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JacksonException {

        JsonNode node = jsonParser.getCodec().readTree(jsonParser);
        String name = node.get("name").asText();
        String description = node.get("description").asText();
        long Id = node.get("id").asLong();
        String catName = node.get("catName").asText();
        String catDesc = node.get("catDesc").asText();
        String createdAt = DateToString.convert(node.get("createdAt").asText(), DateTimeFormatter.ISO_OFFSET_DATE_TIME, "yyyy-MM-dd HH:mm:ss");
        String lastUpdatedAt = DateToString.convert(node.get("lastUpdatedAt").asText(), DateTimeFormatter.ISO_OFFSET_DATE_TIME, "yyyy-MM-dd HH:mm:ss");

        return new ProductDTO(Id, name, description, new CategoryDTO(catName, catDesc), createdAt, lastUpdatedAt);
    }
}
