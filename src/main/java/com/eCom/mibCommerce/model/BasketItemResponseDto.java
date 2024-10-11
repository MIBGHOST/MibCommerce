package com.eCom.mibCommerce.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BasketItemResponseDto {

    private Integer id;
    private String name;
    private String description;
    private String pictureUrl;
    private Long price;
    private String productBrand;
    private String productType;
    private Integer quantity;

}
