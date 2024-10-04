package com.eCom.mibCommerce.dto;

import com.eCom.mibCommerce.entity.Brand;
import com.eCom.mibCommerce.entity.Type;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductResponseDto {
    private Integer id;
    private String name;
    private String description;
    private Long prices;
    private String pictureUrl;
    private String productBrand;
    private String productType;
}
