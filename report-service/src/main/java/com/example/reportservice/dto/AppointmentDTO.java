package com.example.reportservice.dto;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Objects;

public class AppointmentDTO {
    public String customerName;
    public String serviceDescription;
    public BigDecimal price;
    public Date appointmentDate;
    public String employeeName;

    public AppointmentDTO() {
    }

    public AppointmentDTO(String customerName, String serviceDescription, Date appointmentDate, BigDecimal price, String employeeName) {
        this.customerName = customerName;
        this.serviceDescription = serviceDescription;
        this.appointmentDate = appointmentDate;
        this.price = price;
        this.employeeName = employeeName;
    }
    public String getCustomerName() {
        return customerName;
    }

    public String getServiceDescription() {
        return serviceDescription;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public Date getAppointmentDate() {
        return appointmentDate;
    }

    public String getEmployeeName() {
        return employeeName;
    }


    @Override
    public String toString() {
        return "AppointmentDTO{" +
                "customerName='" + customerName + '\'' +
                ", serviceDescription='" + serviceDescription + '\'' +
                ", price=" + price +
                ", appointmentDate=" + appointmentDate +
                ", employeeName='" + employeeName + '\'' +
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
