package com.example.clothStore.controllers.dtos.responses;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class UserResponse {

    private Long id;

    private String name;

    private String email;

    private String cardNumber;

    private String role;
}
