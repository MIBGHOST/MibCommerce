package com.eCom.mibCommerce.service;

import com.eCom.mibCommerce.model.ProductResponseDto;
import com.eCom.mibCommerce.entity.Product;
import com.eCom.mibCommerce.repository.ProductRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class ProductServiceImpl implements ProductService{

    private final ProductRepository productRepository;

    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public ProductResponseDto getProductById(Integer productId) {

        log.info("Fetching the products by Id: {}", productId);
        Product product = productRepository.findById(productId)
                .orElseThrow(()->new RuntimeException("product Id not found!"));
        ProductResponseDto productResponse = convertToProductResponse(product);
        log.info("Fetched the required product!");

        return productResponse;
    }

    @Override
    public Page<ProductResponseDto> getAllProducts(Pageable pageable) {

        log.info("Fetching all the products!");
        Page<Product> productList = productRepository.findAll(pageable);
        Page<ProductResponseDto> response = productList
                .map(this::convertToProductResponse);
        log.info("fetched all the product data!");
        return response;
    }

    private ProductResponseDto convertToProductResponse(Product product) {
        return ProductResponseDto.builder()
                .id(product.getId())
                .name(product.getName())
                .description(product.getDescription())
                .price(product.getPrice())
                .pictureUrl(product.getPictureUrl())
                .productBrand(product.getBrand().getName())
                .productType(product.getType().getName())
                .build();
    }
}
