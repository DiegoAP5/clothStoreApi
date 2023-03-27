package com.example.clothStore.entities.Projections;

import java.time.LocalDate;

public interface SendProjection {

    Long getId();

    Long getUserId();

    Long getOrderId();

    String getAddress();

    String getGuide();

    LocalDate getOrderDate();

    LocalDate getDeliveryDate();

    String getStatusName();
}
