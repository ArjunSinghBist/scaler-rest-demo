package org.scaler.demo.project.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.scaler.demo.project.dto.fakestore.CartDTO;
import org.scaler.demo.project.dto.fakestore.CartRequestDTO;
import org.scaler.demo.project.exceptions.ItemNotFoundException;
import org.scaler.demo.project.exceptions.UpdateCartException;
import org.scaler.demo.project.service.CartService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping("/carts")
public class CartController {

    private final CartService cartService;
    private final Logger logger;

    public CartController(CartService cartService) {
        this.cartService = cartService;
        this.logger = LoggerFactory.getLogger(CartController.class);
    }


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
    public ResponseEntity<CartDTO> getSingleItem(@PathVariable("itemId") long id)
            throws JsonProcessingException {
        return ResponseEntity.ok(cartService.getItem(id));
    }

    // Add new product
    @PostMapping(consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<CartDTO> addProduct(@RequestBody CartRequestDTO cartRequestDTO)
            throws JsonProcessingException {
        logger.info("INSIDE ADDING PRODUCT IN CONTROLLER!!");
        return ResponseEntity.ok(cartService.addProduct(cartRequestDTO));
    }

    // Update existing product
    @PutMapping(path = "/{cartId}", consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<CartDTO> updateProduct(@PathVariable(value = "cartId") long cartId, @RequestBody CartRequestDTO cartRequestDTO)
            throws JsonProcessingException {
        logger.info("UPDATING THE CART! with id: " + cartId);

        CartDTO cartDTO = cartService.updateCart(cartRequestDTO, cartId);
        if(cartDTO == null) {
            throw new UpdateCartException("Updating of cart failed!");
        }

        return ResponseEntity.ok(cartDTO);
    }

    // Delete cart item
    @DeleteMapping(path = "/{itemId}", produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<CartDTO> deleteItem(@PathVariable(value = "itemId") long itemId) {
        logger.info("DELETING THE CART! with id: " + itemId);

        CartDTO cartDTO = cartService.deleteItem(itemId);
        if(cartDTO == null) {
            throw new ItemNotFoundException("Item with id: " + itemId + " does not exist!");
        }

        return ResponseEntity.ok(cartDTO);
    }

}
