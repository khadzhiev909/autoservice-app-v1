package com.example.newpartssertvice.entity;

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
@Table(name = "supplier_parts")
public class SupplierPart {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "supplier_id", nullable = false)
    private Supplier supplier; // Поставщик

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "part_id", nullable = false)
    private Part part; // Связь с запчастью

    @Column(nullable = false)
    private BigDecimal price; // Цена у поставщика

    @Column(nullable = false)
    private LocalDate availabilityDate; // Дата доступности

    @Column(nullable = false)
    private int stockLevel; // Количество доступных на складе у поставщика
}
