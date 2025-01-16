package com.example.reportservice.repository;

import com.example.reportservice.entity.Appointment;
import com.example.reportservice.entity.Services;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;

public interface ServiceRepository extends JpaRepository<Services, Long> {

}
