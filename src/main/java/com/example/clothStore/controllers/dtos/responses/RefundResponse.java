package com.example.clothStore.controllers.dtos.responses;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class RefundResponse {

    private Long id;

    private Long userId;

    private Long orderId;

    private String declaration;

    private String status;
}
