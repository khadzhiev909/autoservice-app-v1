package com.example.inventoryservice.repository;

import com.example.inventoryservice.entity.Supply;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SupplyRepository extends JpaRepository<Supply, Long> {
    // Можно добавить методы для поиска по дате поставки или цене
}
