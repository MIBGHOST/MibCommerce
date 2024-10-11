package com.eCom.mibCommerce.service;

import com.eCom.mibCommerce.entity.Basket;
import com.eCom.mibCommerce.entity.BasketItem;
import com.eCom.mibCommerce.model.BasketItemResponseDto;
import com.eCom.mibCommerce.model.BasketResponseDto;
import com.eCom.mibCommerce.repository.BasketRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
public class BasketServiceImpl implements BasketService {
    private final BasketRepository basketRepository;

    public BasketServiceImpl(BasketRepository basketRepository) {
        this.basketRepository = basketRepository;
    }

    @Override
    public List<BasketResponseDto> getAllBaskets() {
        log.info("Loading baskets...");
        List<Basket> basketList = (List<Basket>) basketRepository.findAll();
        List<BasketResponseDto> basketResponseDtoList = basketList.stream()
                .map(this::convertToBasketResponse)
                .collect(Collectors.toList());
        log.info("Loaded {} baskets.", basketList.size());
        return basketResponseDtoList;
    }

    @Override
    public BasketResponseDto getBasketById(String basketId) {

        log.info("Loading basket by id {}", basketId);
        Optional<Basket> basket = basketRepository.findById(basketId);
        log.info("Loaded basket by id {}", basketId);

        return basket.map(this::convertToBasketResponse).orElse(null);
    }

    @Override
    public void deleteBasketById(String basketId) {
        log.info("Deleting basket by id {}", basketId);
        basketRepository.deleteById(basketId);
        log.info("Deleted basket by id {}", basketId);
    }

    @Override
    public BasketResponseDto createBasket(Basket basket) {
        log.info("Creating basket {}", basket);
        Basket newBasket = basketRepository.save(basket);
        log.info("Created basket {}", basket);
        return convertToBasketResponse(newBasket);
    }

    private BasketResponseDto convertToBasketResponse(Basket basket) {
        if (basket == null) {
            return null;
        }
        List<BasketItemResponseDto> basketItemResponseDtoList = basket.getItems().stream()
                .map(this::convertToBasketItemResponse)
                .collect(Collectors.toList());
        return BasketResponseDto.builder()
                .id(basket.getId())
                .itemResponses(basketItemResponseDtoList)
                .build();
    }

    private BasketItemResponseDto convertToBasketItemResponse(BasketItem basketItem) {
        return BasketItemResponseDto.builder()
                .id(basketItem.getId())
                .name(basketItem.getName())
                .description(basketItem.getDescription())
                .pictureUrl(basketItem.getPictureUrl())
                .price(basketItem.getPrice())
                .productBrand(basketItem.getProductBrand())
                .productType(basketItem.getProductType())
                .quantity(basketItem.getQuantity())
                .build();
    }
}
