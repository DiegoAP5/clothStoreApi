package com.example.clothStore.controllers.dtos.responses;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter @Setter
public class SendResponse {

    private Long id;

    private Long userId;

    private Long orderId;

    private LocalDate orderDate;

    private LocalDate deliveryDate;

    private String address;

    private String guide;

    private String status;
}
