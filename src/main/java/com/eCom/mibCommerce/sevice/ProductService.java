package com.eCom.mibCommerce.sevice;

import com.eCom.mibCommerce.dto.ProductResponseDto;

import java.util.List;

public interface ProductService {
    ProductResponseDto getProductById(Integer productId);
    List<ProductResponseDto> getAllProducts();
}
