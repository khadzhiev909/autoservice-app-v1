package com.example.reportservice.service;

import com.example.reportservice.entity.Invoice;
import com.example.reportservice.repository.InvoiceRepository;
import jakarta.persistence.criteria.CriteriaBuilder;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class InvoiceService {

    private final InvoiceRepository invoiceRepository;


    public List<Invoice> getAllInvoices() {
        return invoiceRepository.findAll();
    }

    public Invoice saveInvoice(Invoice invoice) {
        return invoiceRepository.save(invoice);
    }

    public List<Invoice> getRevenueData(LocalDateTime startDate, LocalDateTime endDate) {
        return invoiceRepository.findAllBetweenDate(startDate, endDate);
    }
}
