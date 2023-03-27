package com.example.clothStore.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Getter @Setter
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private String name;

    @NotNull
    private String email;

    @NotNull
    private String password;

    @NotNull
    private String cardNumber;

    @OneToMany(mappedBy = "user",cascade = CascadeType.ALL)
    private List<Order>orders;

    @OneToMany(mappedBy = "user",cascade = CascadeType.ALL)
    private List<Refund>refunds;

    @OneToMany(mappedBy = "user",cascade = CascadeType.ALL)
    private List<Send>sends;
}
