package com.example.reportservice.repository;

import com.example.reportservice.entity.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface AppointmentRepository extends JpaRepository<Appointment, Long> {
    @Query("SELECT t FROM Appointment t WHERE t.date >= :startDate")
    List<Appointment> findAppointmentByDate(@Param("startDate") LocalDateTime startDate);


}