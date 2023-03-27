package com.example.clothStore.controllers.dtos.requests;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class CreateRefundRequest {

    @NotNull
    private Long userId;

    @NotNull
    private Long orderId;

    @NotNull @NotBlank
    private String status;

    @NotNull @NotBlank
    private String declaration;
}
