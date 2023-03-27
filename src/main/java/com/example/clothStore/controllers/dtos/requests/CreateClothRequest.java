package com.example.clothStore.controllers.dtos.requests;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter @Setter
public class CreateClothRequest {

    @NotNull @NotBlank
    private String name;

    @NotNull @NotBlank
    private String url;

    @NotNull
    private BigDecimal price;
}
