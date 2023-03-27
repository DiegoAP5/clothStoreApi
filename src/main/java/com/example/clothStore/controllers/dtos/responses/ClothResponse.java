package com.example.clothStore.controllers.dtos.responses;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter @Setter
public class ClothResponse {

    private Long id;

    private String name;

    private String url;

    private BigDecimal price;
}
