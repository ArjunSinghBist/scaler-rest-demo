package org.scaler.demo.project.utils.deserializer;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DateToString {

    public static String convert(String dateTime, DateTimeFormatter dateTimeFormatter, String toPattern) {

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(toPattern);
        return LocalDateTime.parse(dateTime, dateTimeFormatter).format(formatter);
    }
}
