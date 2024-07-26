package org.scaler.demo.project.utils.deserializer;

import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import org.scaler.demo.project.dto.CategoryRequestDTO;
import org.scaler.demo.project.dto.ProductRequestDTO;

import java.io.IOException;
import java.util.Objects;

import static org.scaler.demo.project.utils.JsonUtils.*;

public class ProductRequestDTODeserializer extends StdDeserializer<ProductRequestDTO> {

    public ProductRequestDTODeserializer() {
        this(null);
    }

    public ProductRequestDTODeserializer(Class<?> vc) {
        super(vc);
    }

    @Override
    public ProductRequestDTO deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JacksonException {
        JsonNode jsonNode = jsonParser.getCodec().readTree(jsonParser);

        // Get ProductRequestDTO fields
        String name = sanitizeForNull(JsonNodeToText(jsonNode, "name"));
        requireNonNull(name, "Product Name cannot be null!");

        String description = sanitizeForNull(JsonNodeToText(jsonNode, "description"));
        requireNonNull(description, "Product Description cannot be null!");

        String imageUrl = sanitizeForNull(JsonNodeToText(jsonNode, "imageUrl"));

        // Get CategoryDTO fields
        JsonNode catNode = Objects.requireNonNull(jsonNode.path("category"), "Category cannot be null");

        String catName = sanitizeForNull(JsonNodeToText(catNode, "catName"));
        requireNonNull(catName, "Product Category name cannot be null!");

        String catDesc = sanitizeForNull(JsonNodeToText(catNode, "catDesc"));

        CategoryRequestDTO categoryRequestDTO = new CategoryRequestDTO(catName, catDesc);

        return new ProductRequestDTO(name, description, imageUrl, categoryRequestDTO);
    }
}
