package com.example.clothStore.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Getter @Setter
@Table(name = "status")
public class Status {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @OneToMany(mappedBy = "status", cascade = CascadeType.ALL)
    private List<Order>orders;

    @OneToMany(mappedBy = "status",cascade = CascadeType.ALL)
    private List<Refund>refunds;

    @OneToMany(mappedBy = "status",cascade = CascadeType.ALL)
    private List<Send>sends;
}
