package org.scaler.demo.project.exceptions;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.io.IOException;

@RestControllerAdvice
public class RestResponseException{
    Logger  logger = LoggerFactory.getLogger(RestResponseException.class);

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
}
