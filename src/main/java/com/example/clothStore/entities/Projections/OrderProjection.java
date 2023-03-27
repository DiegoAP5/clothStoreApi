package com.example.clothStore.entities.Projections;

import java.math.BigDecimal;

public interface OrderProjection {

    Long getId();

    Long getUserId();

    Long getClothId();

    BigDecimal getPrice();

    BigDecimal getTotal();

    int getQuantity();

    String getStatusName();
}
