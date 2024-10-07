package com.eCom.mibCommerce.service;

import com.eCom.mibCommerce.model.ProductResponseDto;

import java.util.List;

public interface ProductService {
    ProductResponseDto getProductById(Integer productId);
    List<ProductResponseDto> getAllProducts();
}
