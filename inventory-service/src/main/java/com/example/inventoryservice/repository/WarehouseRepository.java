package com.example.inventoryservice.repository;

import com.example.inventoryservice.entity.Warehouse;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WarehouseRepository extends JpaRepository<Warehouse, Long> {
    // Здесь можно добавить методы поиска по названию, адресу и т.д.
}
