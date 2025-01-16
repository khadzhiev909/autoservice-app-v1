package com.example.newpartssertvice.repository;

import com.example.newpartssertvice.entity.SupplierPart;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SupplierPartRepository extends JpaRepository<SupplierPart, Long> {
    List<SupplierPart> findByPartId(Long partId);
}
