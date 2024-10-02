package com.example.bookingservice.entitty;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "service_centers")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ServiceCenter {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "address", nullable = false)
    private String address;

    @Column(name = "phone", nullable = false)
    private String phone;

    @Column(name = "email", nullable = false)
    private String email;

    @OneToMany(mappedBy = "serviceCenter", cascade = CascadeType.ALL)
    private List<Appointment> appointments;
}
