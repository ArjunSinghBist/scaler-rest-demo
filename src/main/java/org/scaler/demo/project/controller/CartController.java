package org.scaler.demo.project.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.scaler.demo.project.dto.CartDTO;
import org.scaler.demo.project.dto.CartRequestDTO;
import org.scaler.demo.project.exceptions.ManualException;
import org.scaler.demo.project.service.CartService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.http.ResponseEntity.ok;

@RestController
@RequestMapping("/carts")
public class CartController {

    @Autowired
    private CartService cartService;

    private Logger logger = LoggerFactory.getLogger(CartController.class);

    // Experimenting with Exception Handlers
  // @ExceptionHandler({IOException.class, RuntimeException.class})
  // public ResponseEntity<List<CartDTO>> handleException() {
  //     return  ResponseEntity.internalServerError().build();
  // }

    // get all cart items
    @GetMapping(produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<List<CartDTO>> cart() throws JsonProcessingException {

        return ResponseEntity.ok(cartService.loadCart());
    }

    // get single item
    @GetMapping("{itemId}")
    public ResponseEntity<CartDTO> getSingleItem(@PathVariable("itemId") long id) throws JsonProcessingException {
        return ResponseEntity.ok(cartService.getItem(id));
    }

    // Add new product
    @PostMapping(consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<CartDTO> addProduct(@RequestBody CartRequestDTO cartRequestDTO) throws JsonProcessingException {
        logger.info("INSIDE ADDING PRODUCT IN CONTROLLER!!");
        return ResponseEntity.ok(cartService.addProduct(cartRequestDTO));
    }

}
