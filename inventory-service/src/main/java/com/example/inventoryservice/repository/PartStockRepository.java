package com.example.inventoryservice.repository;

import com.example.inventoryservice.entity.PartStock;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PartStockRepository extends JpaRepository<PartStock, Long> {
    // Методы для поиска запчастей по складу и наоборот
}
