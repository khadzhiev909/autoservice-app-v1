package com.example.bookingservice.repository;

import com.example.bookingservice.entitty.Appointment;
import com.example.bookingservice.entitty.ServiceCenter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;

@Repository
public interface AppointmentRepository extends JpaRepository<Appointment, Long> {

    boolean existsByAppointmentDateAndServiceCenter(LocalDateTime appointmentDate, ServiceCenter serviceCenter);
}
