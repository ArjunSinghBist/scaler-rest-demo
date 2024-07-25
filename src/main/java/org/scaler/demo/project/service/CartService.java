package org.scaler.demo.project.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.scaler.demo.project.dto.fakestore.CartDTO;
import org.scaler.demo.project.dto.fakestore.CartRequestDTO;

import java.util.List;

public interface CartService {

    List<CartDTO> loadCart() throws JsonProcessingException;

    CartDTO getItem(long id) throws JsonProcessingException;

    CartDTO addProduct(CartRequestDTO cartRequestDTO) throws JsonProcessingException;

    CartDTO updateCart(CartRequestDTO cartRequestDTO, long cartId);

    CartDTO deleteItem(long itemId);
}
