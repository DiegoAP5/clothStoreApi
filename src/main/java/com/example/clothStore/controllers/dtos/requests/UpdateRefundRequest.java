package com.example.clothStore.controllers.dtos.requests;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class UpdateRefundRequest {

    private Long id;

    @NotBlank
    private String status;

    private String declaration;
}
