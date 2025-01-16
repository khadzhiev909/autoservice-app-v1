package com.example.newpartssertvice.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "parts")
public class Part {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name; // Название запчасти

    @Column(nullable = false)
    private String category; // Категория запчасти

    @Column(nullable = false)
    private String brand; // Бренд

    @Column(nullable = false)
    private BigDecimal price; // Цена

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Condition condition; // Состояние (новая/б/у)

    @Column(nullable = false)
    private int quantity; // Количество на складе

    private String description; // Описание

    @Column(nullable = false, unique = true)
    private String partNumber; // Номер запчасти

    @Column(nullable = false)
    private LocalDate addedDate; // Дата добавления

    @OneToMany(mappedBy = "part", cascade = CascadeType.ALL)
    private List<SupplierPart> supplierParts; // Поставки от поставщиков
}
