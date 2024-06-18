package org.scaler.demo.project.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.scaler.demo.project.dto.CartDTO;
import org.scaler.demo.project.dto.CartRequestDTO;

import java.util.List;

public interface CartService {

    List<CartDTO> loadCart() throws JsonProcessingException;

    CartDTO getItem(long id) throws JsonProcessingException;

    CartDTO addProduct(CartRequestDTO cartRequestDTO) throws JsonProcessingException;
}
