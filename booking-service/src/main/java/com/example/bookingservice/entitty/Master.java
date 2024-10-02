package com.example.bookingservice.entitty;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "masters")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Master {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "first_name", nullable = false)
    private String firstName;

    @Column(name = "last_name", nullable = false)
    private String lastName;

    @Column(name = "specialization")
    private String specialization;

    @Column(name = "available", nullable = false)
    private boolean available;

    @OneToMany(mappedBy = "master", cascade = CascadeType.ALL)
    private List<Appointment> appointments;
}
