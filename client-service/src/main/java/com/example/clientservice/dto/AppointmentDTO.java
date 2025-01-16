package com.example.clientservice.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.Objects;

public class AppointmentDTO {
    public String customerName;
    public String serviceDescription;
    public BigDecimal price;
    public LocalDateTime appointmentDate;
    public String employeeName;

    public String getCustomerName() {
        return customerName;
    }

    public String getServiceDescription() {
        return serviceDescription;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public LocalDateTime getAppointmentDate() {
        return appointmentDate;
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public AppointmentDTO() {
    }

    public AppointmentDTO(String customerName, String serviceDescription, LocalDateTime appointmentDate, BigDecimal price, String employeeName) {
        this.customerName = customerName;
        this.serviceDescription = serviceDescription;
        this.appointmentDate = appointmentDate;
        this.price = price;
        this.employeeName = employeeName;
    }

    @Override
    public String toString() {
        return "AppointmentDTO: \n" +
                "customerName='" + customerName + '\'' +
                ", \nserviceDescription='" + serviceDescription + '\'' +
                ", \nprice=" + price +
                ", \nappointmentDate=" + appointmentDate +
                ", \nemployeeName='" + employeeName + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AppointmentDTO that = (AppointmentDTO) o;
        return Objects.equals(customerName, that.customerName) && Objects.equals(serviceDescription, that.serviceDescription) && Objects.equals(price, that.price) && Objects.equals(appointmentDate, that.appointmentDate) && Objects.equals(employeeName, that.employeeName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(customerName, serviceDescription, price, appointmentDate, employeeName);
    }
}
