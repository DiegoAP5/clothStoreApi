package com.example.clothStore.controllers.dtos.requests;

import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter @Setter
public class CreateSendRequest {

    @NotNull
    private Long userId;

    @NotNull
    private Long orderId;

    @NotNull @NotBlank
    private String status;

    @PastOrPresent
    private LocalDate orderDate;

    @FutureOrPresent
    private LocalDate deliveryDate;

    @NotNull @NotBlank
    private String address;

    @NotNull @NotBlank
    private String guide;
}
