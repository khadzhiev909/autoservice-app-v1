package com.example.reportservice.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Data
@Entity
public class Appointment {//запись на обслуживание
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDateTime date;

    private String status;


    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @ManyToMany
    @JoinTable(
            name = "appointment_services",
            joinColumns = @JoinColumn(name = "appointment_id"),
            inverseJoinColumns = @JoinColumn(name = "service_id")
    )
    private List<Services> services;
    public Appointment() {
    }

    public String getCustomerName() {
        return customer != null ? customer.getName() : null;
    }

    public String getServiceNames() {
        return services != null ? services.stream()
                .map(Services::getName)
                .collect(Collectors.joining(", ")) : null;
    }
    public BigDecimal getTotalServicePrice() {
        return services != null ? services.stream()
                .map(Services::getPrice)  // assuming there's a getPrice() method in Services
                .reduce(BigDecimal.ZERO, BigDecimal::add) : BigDecimal.ZERO;
    }
    public Date getAppointmentDate() {
        return Date.from(this.date.atZone(ZoneId.systemDefault()).toInstant());
    }


}



/*package com.example.reportservice.entity;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;
import java.util.List;

@Data
@Entity
public class Appointment {//запись на обслуживание
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDateTime date;

    private String status;

    private String customerName;


    private String serviceName;
}
*/