package org.scaler.demo.project.utils.deserializer;

import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import org.scaler.demo.project.dto.CategoryDTO;
import org.scaler.demo.project.dto.ProductDTO;

import java.io.IOException;
import java.time.format.DateTimeFormatter;

public class ProductDTODeserializer extends StdDeserializer<ProductDTO> {

    public ProductDTODeserializer() {
        this(null);
    }

    public ProductDTODeserializer(Class<?> vc) {
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
        String imageUrl = node.get("imageUrl").asText();
        String createdAt = DateToString.convert(node.get("createdAt").asText(), DateTimeFormatter.ISO_OFFSET_DATE_TIME, "yyyy-MM-dd HH:mm:ss");
        String lastUpdatedAt = DateToString.convert(node.get("lastUpdatedAt").asText(), DateTimeFormatter.ISO_OFFSET_DATE_TIME, "yyyy-MM-dd HH:mm:ss");

        return new ProductDTO(Id, name, description,imageUrl, new CategoryDTO(catName, catDesc), createdAt, lastUpdatedAt);
    }
}
