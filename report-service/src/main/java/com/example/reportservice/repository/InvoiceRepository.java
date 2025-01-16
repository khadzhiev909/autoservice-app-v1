package com.example.reportservice.repository;

import com.example.reportservice.entity.Invoice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface InvoiceRepository extends JpaRepository<Invoice, Long> {
    @Query("select i from Invoice i where i.issuedAt BETWEEN :startDate and :endDate")
    List<Invoice> findAllBetweenDate(@Param("startDate") LocalDateTime startDate,
                                     @Param("endDate") LocalDateTime endDate);

}