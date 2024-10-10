package com.eCom.mibCommerce.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomErrorResponseDto {
    private String message;
    private String error;
    private HttpStatus status;
}
