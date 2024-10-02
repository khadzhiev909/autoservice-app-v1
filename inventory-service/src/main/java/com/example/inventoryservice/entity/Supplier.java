package com.example.inventoryservice.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "suppliers")
public class Supplier { //Поставщик

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String contactInfo;

    private String address;

    private String phone;

    private float rating;

    @OneToMany(mappedBy = "supplier", cascade = CascadeType.ALL)
    private List<Supply> supplies;

    // Getters and Setters
    // ...
}
