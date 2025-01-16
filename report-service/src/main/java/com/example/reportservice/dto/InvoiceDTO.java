package com.example.reportservice.dto;

import com.example.reportservice.entity.Appointment;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Objects;

public class InvoiceDTO {
    private BigDecimal amount;

    private LocalDateTime issuedAt;

    private String status;

    private Appointment appointment;

    private Integer serviceCount;

    public InvoiceDTO() {
    }

    @Override
    public String toString() {
        return "InvoiceDTO{" +
                "amount=" + amount +
                ", issuedAt=" + issuedAt +
                ", status='" + status + '\'' +
                ", appointment=" + appointment +
                ", serviceCount=" + serviceCount +
                '}';
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public LocalDateTime getIssuedAt() {
        return issuedAt;
    }

    public void setIssuedAt(LocalDateTime issuedAt) {
        this.issuedAt = issuedAt;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Appointment getAppointment() {
        return appointment;
    }

    public void setAppointment(Appointment appointment) {
        this.appointment = appointment;
    }

    public Integer getServiceCount() {
        return serviceCount;
    }

    public void setServiceCount(Integer serviceCount) {
        this.serviceCount = serviceCount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        InvoiceDTO that = (InvoiceDTO) o;
        return Objects.equals(amount, that.amount) && Objects.equals(issuedAt, that.issuedAt) && Objects.equals(status, that.status) && Objects.equals(appointment, that.appointment) && Objects.equals(serviceCount, that.serviceCount);
    }

    @Override
    public int hashCode() {
        return Objects.hash(amount, issuedAt, status, appointment, serviceCount);
    }
}
