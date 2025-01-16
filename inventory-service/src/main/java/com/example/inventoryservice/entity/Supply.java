package com.example.inventoryservice.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "supplies")
public class Supply { //Поставки

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "supplier_id", nullable = false)
    private Supplier supplier;

//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "part_id", nullable = false)
//    private Part part;

    @Column(nullable = false)
    private BigDecimal supplyPrice;

    @Column(nullable = false)
    private int quantity;

    @Column(nullable = false)
    private LocalDate supplyDate;

    // Getters and Setters
    // ...
}
