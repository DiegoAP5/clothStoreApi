package com.example.clothStore.controllers.dtos.requests;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter @Setter
public class CreateOrderRequest {

    @NotNull
    private Long userId;

    @NotNull
    private Long clothId;

    @Positive
    private int quantity;

    @NotNull @NotBlank
    private String statusName;

    private BigDecimal price;
}
