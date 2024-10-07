package com.eCom.mibCommerce.controller;

import com.eCom.mibCommerce.model.BrandResponseDto;
import com.eCom.mibCommerce.model.ProductResponseDto;
import com.eCom.mibCommerce.model.TypeResponseDto;
import com.eCom.mibCommerce.service.BrandService;
import com.eCom.mibCommerce.service.ProductService;
import com.eCom.mibCommerce.service.TypeService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductController {
    private final ProductService productService;
    private final BrandService brandService;
    private final TypeService typeService;

    public ProductController(ProductService productService, BrandService brandService, TypeService typeService) {
        this.productService = productService;
        this.brandService = brandService;
        this.typeService = typeService;
    }

    @GetMapping("/{Id}")
    public ResponseEntity<ProductResponseDto> getProductById(@PathVariable("Id")Integer productId){
        ProductResponseDto response = productService.getProductById(productId);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<Page<ProductResponseDto>> getProducts(@PageableDefault(size = 10) Pageable pageable){
        Page<ProductResponseDto> productsRes = productService.getAllProducts(pageable);
        return new ResponseEntity<>(productsRes, HttpStatus.OK);
    }

    @GetMapping("/brands")
    public ResponseEntity<List<BrandResponseDto>> getBrands(){
        List<BrandResponseDto> brandRes = brandService.getAllBrands();
        return new ResponseEntity<>(brandRes, HttpStatus.OK);
    }

    @GetMapping("/types")
    public ResponseEntity<List<TypeResponseDto>> getTypes(){
        List<TypeResponseDto> typeRes = typeService.getAllTypes();
        return new ResponseEntity<>(typeRes, HttpStatus.OK);
    }
}
