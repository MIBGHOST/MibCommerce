package com.eCom.mibCommerce.sevice;

import com.eCom.mibCommerce.dto.BrandResponseDto;
import com.eCom.mibCommerce.entity.Brand;
import com.eCom.mibCommerce.repository.BrandRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class BrandServiceImpl implements BrandService{

    private final BrandRepository brandRepository;

    public BrandServiceImpl(BrandRepository brandRepository) {
        this.brandRepository = brandRepository;
    }

    @Override
    public List<BrandResponseDto> getAllBrands() {

        log.info("fetching the brands!");
        List<Brand> brandList = brandRepository.findAll();
        List<BrandResponseDto> responses = brandList.stream()
                .map(this::convertToBrandResponse)
                .collect(Collectors.toList());
        log.info("Fetched All brands!");

        return responses;
    }

    private BrandResponseDto convertToBrandResponse(Brand brand) {
        return BrandResponseDto.builder()
                .id(brand.getId())
                .name(brand.getName())
                .build();
    }
}
