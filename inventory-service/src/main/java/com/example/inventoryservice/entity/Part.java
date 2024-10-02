package com.example.inventoryservice.entity;
import com.fasterxml.jackson.annotation.JsonManagedReference;
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
public class Part { //Запчасть

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String category;

    @Column(nullable = false)
    private String brand;

    @Column(nullable = false)
    private BigDecimal price;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Condition condition;

    @Column(nullable = false)
    private int quantity;

    private String description;

    @Column(nullable = false, unique = true)
    private String partNumber;

    @Column(nullable = false)
    private LocalDate addedDate;

    @OneToMany(mappedBy = "part", cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<PartStock> partStocks;

    @OneToMany(mappedBy = "part", cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<Supply> supplies;

    // Getters and Setters
    // ...
}

enum Condition {
    NEW, USED
}