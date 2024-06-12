package org.scaler.demo.project.service.impl;

import org.scaler.demo.project.dto.CartDTO;
import org.scaler.demo.project.service.CartService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;


@Service
public class CartServiceImpl implements CartService {

    @Value("${CART_API}")
    private String EXTERNAL_CART_API;

    Logger logger = LoggerFactory.getLogger(CartServiceImpl.class);

    @Override
    public List<CartDTO> loadCart() {
        RestTemplate restTemplate = new RestTemplate();

        // BELOW ARE DIFFERENT WAYS TO HANDLE THE ARRAY OF RESPONSE OBJECTS THAT ARE RETURNED
        // return responseMethod1(restTemplate);
//        return restTemplate.getForObject(EXTERNAL_CART_API, String.class);
//        return "Returning all items in the cart!";
        return responseMethod2(restTemplate);

    }

    // Create response using Parametrized Type Reference
    private List<CartDTO> responseMethod2(RestTemplate restTemplate) {

        ResponseEntity<List<CartDTO>> cartDTOList = restTemplate.exchange(EXTERNAL_CART_API, HttpMethod.GET
                , null, new ParameterizedTypeReference<List<CartDTO>>() {}
                );

        return cartDTOList.getBody();
    }

    // We create an Array of CartDTO and then convert that to List<CartDTO>
    private List<CartDTO> responseMethod1(RestTemplate restTemplate) {

        ResponseEntity<CartDTO[]> cartDTO = restTemplate.getForEntity(EXTERNAL_CART_API, CartDTO[].class);
        logger.info("REPLYING USING METHOD1");

        // converting CartDTO array to List<CartDTO>
        return Arrays.stream(Objects.requireNonNull(cartDTO.getBody(), "Response not received!!")).toList();
    }
}
