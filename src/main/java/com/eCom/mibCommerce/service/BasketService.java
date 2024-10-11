package com.eCom.mibCommerce.service;

import com.eCom.mibCommerce.entity.Basket;
import com.eCom.mibCommerce.model.BasketResponseDto;

import java.util.List;

public interface BasketService {
    List<BasketResponseDto> getAllBaskets();
    BasketResponseDto getBasketById(String basketId);
    void deleteBasketById(String basketId);
    BasketResponseDto createBasket(Basket basket);

    //    List<BasketResponse> getAllBasketsByCustomerId(String customerId);
}
