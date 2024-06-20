package org.scaler.demo.project.utils.deserializer;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAccessor;

public class DateDeserializer extends StdDeserializer<LocalDateTime> {

    public DateDeserializer() {
        this(null);
    }

    public DateDeserializer(Class<?> src) {
        super(src);
    }

    @Override
    public LocalDateTime deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
        String date = jsonParser.getText();

        // Here we are only Looking for LocalDate and LocalDate time format.
        // pattern after yyyy-MM-dd is optional. We can pick and choose the optional part as well
        // as parse more instance types which can possible match. The worst match will be at last.
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd['T'HH:mm:ss.SSS'Z']");
        TemporalAccessor accessor = formatter.parseBest(date, LocalDateTime::from, LocalDate::from);

        if(accessor instanceof LocalDateTime) {
            return (LocalDateTime) accessor;
        }
        else {
            LocalDate locDate = (LocalDate) accessor;
            return locDate.atStartOfDay();
        }
    }
}
