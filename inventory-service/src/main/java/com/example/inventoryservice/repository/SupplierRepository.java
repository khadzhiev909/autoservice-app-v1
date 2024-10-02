package com.example.inventoryservice.repository;

import com.example.inventoryservice.entity.Supplier;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SupplierRepository extends JpaRepository<Supplier, Long> {
    // Методы поиска по имени поставщика или рейтингу
}
