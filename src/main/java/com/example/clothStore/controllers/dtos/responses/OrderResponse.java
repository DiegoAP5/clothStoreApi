package com.example.clothStore.controllers.dtos.responses;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter @Setter
public class OrderResponse {

    private Long id;

    private Long userId;

    private Long clothId;

    private BigDecimal price;

    private BigDecimal total;

    private int quantity;

    private String statusName;
}
