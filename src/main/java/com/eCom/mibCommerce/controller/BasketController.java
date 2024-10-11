package com.eCom.mibCommerce.controller;

import com.eCom.mibCommerce.entity.Basket;
import com.eCom.mibCommerce.entity.BasketItem;
import com.eCom.mibCommerce.model.BasketItemResponseDto;
import com.eCom.mibCommerce.model.BasketResponseDto;
import com.eCom.mibCommerce.service.BasketService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/basket")
public class BasketController {

    private final BasketService basketService;

    public BasketController(BasketService basketService) {
        this.basketService = basketService;
    }

    @GetMapping
    public List<BasketResponseDto> getAllBaskets() {
        return basketService.getAllBaskets();
    }

    @GetMapping("/{basketId}")
    public BasketResponseDto getBasketById(@PathVariable String basketId) {
        return basketService.getBasketById(basketId);
    }

    @DeleteMapping("/{basketId}")
    public void deleteBasketById(@PathVariable String basketId) {
        basketService.deleteBasketById(basketId);
    }
    @PostMapping
    public ResponseEntity<BasketResponseDto> createBasket(@RequestBody BasketResponseDto basketResponseDto) {
        Basket basket = convertToBasketEntity(basketResponseDto);
        BasketResponseDto createdBasket = basketService.createBasket(basket);
        return new ResponseEntity<>(createdBasket, HttpStatus.CREATED);
    }

    private Basket convertToBasketEntity(BasketResponseDto basketResponseDto) {
        Basket basket = new Basket();
        basket.setId(basketResponseDto.getId());
        basket.setItems(mapBasketItemResponseToEntities(basketResponseDto.getItemResponses()));
        return basket;
    }

    private List<BasketItem> mapBasketItemResponseToEntities(List<BasketItemResponseDto> itemResponses) {
        return itemResponses.stream()
                .map(this::convertToBasketItemEntity)
                .collect(Collectors.toList());
    }

    private BasketItem convertToBasketItemEntity(BasketItemResponseDto basketItemResponseDto) {
        BasketItem basketItem = new BasketItem();
        basketItem.setId(basketItemResponseDto.getId());
        basketItem.setName(basketItemResponseDto.getName());
        basketItem.setDescription(basketItemResponseDto.getDescription());
        basketItem.setPrice(basketItemResponseDto.getPrice());
        basketItem.setPictureUrl(basketItemResponseDto.getPictureUrl());
        basketItem.setProductBrand(basketItemResponseDto.getProductBrand());
        basketItem.setProductType(basketItemResponseDto.getProductType());
        basketItem.setQuantity(basketItemResponseDto.getQuantity());
        return basketItem;
    }
}
