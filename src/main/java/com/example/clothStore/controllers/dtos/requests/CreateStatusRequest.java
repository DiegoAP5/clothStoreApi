package com.example.clothStore.controllers.dtos.requests;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class CreateStatusRequest {

    @NotNull @NotBlank
    private String name;
}
