package com.example.paymentservice.repository;

import com.example.paymentservice.entity.Invoice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InvoiceRepository extends JpaRepository<Invoice, Long> {
    // Здесь можно добавить дополнительные методы для поиска счетов по различным критериям, если необходимо.
}
