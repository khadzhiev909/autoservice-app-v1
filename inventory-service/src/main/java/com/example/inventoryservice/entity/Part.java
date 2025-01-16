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
    private int quantity;

    private String description;

    @Column(nullable = false, unique = true)
    private String partNumber;

    @Column(nullable = false)
    private LocalDate addedDate;

    public Part(String name, String category, String brand, BigDecimal price, int quantity, String description, String partNumber, LocalDate addedDate) {
        this.name = name;
        this.category = category;
        this.brand = brand;
        this.price = price;
        this.quantity = quantity;
        this.description = description;
        this.partNumber = partNumber;
        this.addedDate = addedDate;
    }

    //    @OneToMany(mappedBy = "part", cascade = CascadeType.ALL)
//    @JsonManagedReference
//    private List<PartStock> partStocks;
//
//    @OneToMany(mappedBy = "part", cascade = CascadeType.ALL)
//    @JsonManagedReference
//    private List<Supply> supplies;

    // Getters and Setters
    // ...
}
