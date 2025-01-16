package com.example.reportservice.entity;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
@JsonSerialize
public class Customer {//информация о клиенте
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String firstName;

    private String lastName;

    private String email;

    private String phoneNumber;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "customer")
    transient List<Appointment> appointments;

    public String getName() {
        return firstName + " " + lastName;
    }
}
