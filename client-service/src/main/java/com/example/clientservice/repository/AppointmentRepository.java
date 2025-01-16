package com.example.clientservice.repository;

import com.example.clientservice.entity.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.nio.file.LinkOption;

@Repository
public interface AppointmentRepository extends JpaRepository<Appointment, Long> {
}
