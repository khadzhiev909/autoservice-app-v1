package com.example.reportservice.repository;

import com.example.reportservice.entity.SimplePart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SimplePartRepository extends JpaRepository<SimplePart, Long> {

}
