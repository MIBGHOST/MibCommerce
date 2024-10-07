package com.eCom.mibCommerce.service;

import com.eCom.mibCommerce.model.ProductResponseDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ProductService {
    ProductResponseDto getProductById(Integer productId);
    Page<ProductResponseDto> getAllProducts(Pageable pageable);
}
