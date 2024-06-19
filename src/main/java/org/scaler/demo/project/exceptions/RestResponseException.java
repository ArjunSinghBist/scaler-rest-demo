package org.scaler.demo.project.exceptions;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.io.IOException;
import java.time.format.DateTimeParseException;

@RestControllerAdvice
public class RestResponseException{
    private final HttpServletResponse httpServletResponse;
    Logger  logger = LoggerFactory.getLogger(RestResponseException.class);

    public RestResponseException(HttpServletResponse httpServletResponse) {
        this.httpServletResponse = httpServletResponse;
    }

    @ExceptionHandler(value = {RuntimeException.class, IOException.class})
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR, reason = "Something went wrong!")
    public void exception(RuntimeException e){
        e.printStackTrace();
    }

    @ExceptionHandler(value = {JsonProcessingException.class})
    @ResponseStatus(value = HttpStatus.FAILED_DEPENDENCY, reason = "Failed to process response")
    public void JasperException(JsonProcessingException e){
        e.printStackTrace();
    }

    @ExceptionHandler(value = {ItemNotFoundException.class})
    @ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "Please check the parameters!")
    public void ItemNotFound(ItemNotFoundException e){
        e.printStackTrace();
    }

    @ExceptionHandler(value = {MethodArgumentTypeMismatchException.class})
    @ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "Parameter value is of incorrect type!")
    public void ArgumentTypeMismatch(MethodArgumentTypeMismatchException e){
        e.printStackTrace();
    }

    @ExceptionHandler(value = {JsonMappingException.class, DateTimeParseException.class, HttpMessageNotReadableException.class})
    @ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "JSON payload is not correct!")
    public void JsonException(JsonMappingException e){
        e.printStackTrace();
    }

    @ExceptionHandler(value = {UpdateCartException.class})
    @ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "Update to Cart failed!")
    public void UpdateCartException(UpdateCartException e){
        e.printStackTrace();
    }
}
