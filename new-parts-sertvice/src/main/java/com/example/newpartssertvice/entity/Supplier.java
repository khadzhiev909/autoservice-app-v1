package com.example.newpartssertvice.entity;

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
public class Supplier {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name; // Название поставщика

    @Column(nullable = false)
    private String contactPerson; // Ответственное лицо

    @Column(nullable = false)
    private String phoneNumber; // Номер телефона

    @OneToMany(mappedBy = "supplier", cascade = CascadeType.ALL)
    private List<SupplierPart> supplierParts; // Запчасти, предоставленные поставщиком
}
