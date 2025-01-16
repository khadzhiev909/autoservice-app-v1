package com.example.reportservice.entity;

import lombok.Data;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Entity
public class Invoice {//информация о счете (оплате)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private BigDecimal amount;

    private LocalDateTime issuedAt;

    private String status;

    @ManyToOne
    @JoinColumn(name = "appointment_id")
    private Appointment appointment;

    // Добавление поля для количества услуг
    private Integer serviceCount;
    public void calculateRevenue() {
        if (serviceCount != null && serviceCount > 0 && amount != null) {
            this.amount = amount.multiply(BigDecimal.valueOf(serviceCount));
        }
    }
}
