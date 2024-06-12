package org.scaler.demo.project.service;

import org.scaler.demo.project.dto.CartDTO;

import java.util.List;

public interface CartService {

    List<CartDTO> loadCart();
}
