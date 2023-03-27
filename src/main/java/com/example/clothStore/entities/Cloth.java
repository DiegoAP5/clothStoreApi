package com.example.clothStore.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;

@Entity
@Getter @Setter
@Table(name = "cloths")
public class Cloth {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Column(length = 400)
    private String url;

    private BigDecimal price;

    @OneToMany(mappedBy = "cloth",cascade = CascadeType.ALL)
    private List<Order>orders;
}
