package com.example.inventoryservice.repository;

import com.example.inventoryservice.entity.Part;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.data.domain.Page;

@Repository
public interface PartRepository extends JpaRepository<Part, Long> {
    @Query("Select p from Part p")
    Page<Part> getAllParts(Pageable pageable);
}
