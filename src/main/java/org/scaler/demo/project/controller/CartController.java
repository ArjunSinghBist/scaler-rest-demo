package org.scaler.demo.project.controller;

import org.scaler.demo.project.dto.CartDTO;
import org.scaler.demo.project.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
@RequestMapping("/cart")
public class CartController {

    @Autowired
    private CartService cartService;

    @GetMapping()
    public List<CartDTO> cart() {
        return cartService.loadCart();
    }
}
