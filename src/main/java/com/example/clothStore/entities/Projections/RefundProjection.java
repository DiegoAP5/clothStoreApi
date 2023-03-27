package com.example.clothStore.entities.Projections;

public interface RefundProjection {

    Long getId();

    Long getUserId();

    Long getOrderId();

    String getDeclaration();

    String getStatusName();
}
