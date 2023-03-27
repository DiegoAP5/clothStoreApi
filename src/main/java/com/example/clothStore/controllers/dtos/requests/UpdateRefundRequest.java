package com.example.clothStore.controllers.dtos.requests;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class UpdateRefundRequest {

    @NotBlank
    private String status;
}
