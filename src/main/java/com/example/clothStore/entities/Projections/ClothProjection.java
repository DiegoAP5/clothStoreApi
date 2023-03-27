package com.example.clothStore.entities.Projections;

import java.math.BigDecimal;

public interface ClothProjection {

    Long getId();

    String getName();

    BigDecimal getPrice();

    String getUrl();
}
