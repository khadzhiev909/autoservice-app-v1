package com.example.newpartssertvice.repository;

import com.example.newpartssertvice.entity.Supplier;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SupplierRepository extends JpaRepository<Supplier, Long> {
}
