package org.scaler.demo.project.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.scaler.demo.project.dto.CartDTO;
import org.scaler.demo.project.dto.CartRequestDTO;
import org.scaler.demo.project.exceptions.ItemNotFoundException;
import org.scaler.demo.project.service.CartService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;


@Service
public class CartServiceImpl implements CartService {

    @Value("${CART_API}")
    private String EXTERNAL_CART_API;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private ObjectMapper objectMapper;

    Logger logger = LoggerFactory.getLogger(CartServiceImpl.class);

/*
    @Override
    public List<CartDTO> loadCart() {
        // BELOW ARE DIFFERENT WAYS TO HANDLE THE ARRAY OF RESPONSE OBJECTS THAT ARE RETURNED
        // return responseMethod1(restTemplate);
//        return restTemplate.getForObject(EXTERNAL_CART_API, String.class);
//        return "Returning all items in the cart!";
        return responseMethod2(restTemplate);
    }
*/

    // Create response using Parametrized Type Reference
    private List<CartDTO> responseMethod2(RestTemplate restTemplate) {

        ResponseEntity<List<CartDTO>> cartDTOList = restTemplate.exchange(EXTERNAL_CART_API, HttpMethod.GET
                , null, new ParameterizedTypeReference<List<CartDTO>>() {
                }
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

    @Override
    public List<CartDTO> loadCart() throws JsonProcessingException {

        ResponseEntity<String> responseEntity = restTemplate.getForEntity(EXTERNAL_CART_API, String.class);
        List<CartDTO> response = null;

        // check if we get have proper response
        if (responseEntity.getStatusCode().is2xxSuccessful()) {
            response = objectMapper.readValue(responseEntity.getBody(), new TypeReference<List<CartDTO>>() {
            });
        } else {
            logger.error("RESPONSE IS NOT OK!!  => {}", responseEntity.getStatusCode());
            logger.error(String.valueOf(responseEntity.getHeaders()));
        }

        return response;
    }

    @Override
    public CartDTO getItem(long id) throws JsonProcessingException {
        // create the url
        String url = EXTERNAL_CART_API + "/" + id;
        logger.info("URL for 1 item id => " + url);

        // call the api
        ResponseEntity<String> responseEntity = restTemplate.getForEntity(url, String.class);
        CartDTO response = null;

        if (responseEntity.getStatusCode().is2xxSuccessful()) {
            response = objectMapper.readValue(responseEntity.getBody(), CartDTO.class);
        } else {
            logger.info("NO RESPONSE FROM API => {}", responseEntity.getStatusCode());
        }

        if (response == null)
            throw new ItemNotFoundException("Item with Id " + id + " not found");

        // return the result
        return response;
    }

    // add new item
    @Override
    public CartDTO addProduct(CartRequestDTO cartRequestDTO) throws JsonProcessingException {
        logger.info("Adding product started!");
        // Create Http Entity
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> entity = new HttpEntity<>(objectMapper.writeValueAsString(cartRequestDTO), headers);

        // call the API
        ResponseEntity<String> responseEntity = restTemplate.postForEntity(EXTERNAL_CART_API, entity, String.class);

        // return the new added item
        CartDTO cartDTO = null;
        if (responseEntity.getStatusCode().is2xxSuccessful()) {
            cartDTO = objectMapper.readValue(responseEntity.getBody(), CartDTO.class);
        }

        return cartDTO;
    }

    // update cart
    @Override
    public CartDTO updateCart(CartRequestDTO cartRequestDTO, long cartId) {

        // put returns no value
//        restTemplate.put(EXTERNAL_CART_API + "/" + cartId, cartRequestDTO);

        // Using exchange to carry out PUT
        // prepare the URL and HTTP Entity
        String putURL = EXTERNAL_CART_API + "/{id}";
        HttpEntity<CartRequestDTO> cartHttpEntity = new HttpEntity<>(cartRequestDTO);

        // call exchange
        ResponseEntity<CartDTO> responseEntity = restTemplate.exchange(putURL
                , HttpMethod.PUT
                , cartHttpEntity
                , new ParameterizedTypeReference<>() {
                }
                , new Object[]{cartId});

        if (responseEntity.getStatusCode().is2xxSuccessful()) {
            return responseEntity.getBody();
        }

        return null;
    }

    // delete item
    @Override
    public CartDTO deleteItem(long itemId) {

        // We have to use exchange method to get a ResponseEntity
        // create URL
        String url = EXTERNAL_CART_API + "/{id}";

        // make the delete call
        ResponseEntity<CartDTO> responseEntity = restTemplate.exchange(url
                , HttpMethod.DELETE
                , null
                , new ParameterizedTypeReference<>() {
                }
                , new Object[]{itemId});

        // check if response is ok
        CartDTO response = null;
        if (responseEntity.getStatusCode().is2xxSuccessful()) {
            response = responseEntity.getBody();
        }

        return response;
    }
}
