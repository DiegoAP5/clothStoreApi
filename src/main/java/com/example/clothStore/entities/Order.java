package com.example.clothStore.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;

@Entity
@Getter @Setter
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id",referencedColumnName = "id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "cloth_id",referencedColumnName = "id")
    private Cloth cloth;

    private int quantity;

    private BigDecimal price;

    private BigDecimal total;

    private String address;

    private String guide;

    @ManyToOne
    @JoinColumn(name = "status_id",referencedColumnName = "id")
    private Status status;

    @OneToMany(mappedBy = "order",cascade = CascadeType.ALL)
    private List<Refund>refunds;

    @OneToMany(mappedBy = "order",cascade = CascadeType.ALL)
    private List<Send>sends;
}
