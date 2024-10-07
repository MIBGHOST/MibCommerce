package com.eCom.mibCommerce.service;

import com.eCom.mibCommerce.model.TypeResponseDto;
import com.eCom.mibCommerce.entity.Type;
import com.eCom.mibCommerce.repository.TypeRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class TypeServiceImpl implements TypeService{

    private final TypeRepository typeRepository;

    public TypeServiceImpl(TypeRepository typeRepository) {
        this.typeRepository = typeRepository;
    }

    @Override
    public List<TypeResponseDto> getAllTypes() {

        log.info("Fetching the types!");
        List<Type> typeList = typeRepository.findAll();
        List<TypeResponseDto> responses = typeList.stream()
                .map(this::convertToTypeResponse)
                .collect(Collectors.toList());
        log.info("Fetched all Types!");

        return responses;
    }

    private TypeResponseDto convertToTypeResponse(Type type) {
        return TypeResponseDto.builder()
                .id(type.getId())
                .name(type.getName())
                .build();
    }
}
