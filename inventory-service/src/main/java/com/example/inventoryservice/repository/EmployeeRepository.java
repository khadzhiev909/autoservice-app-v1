package com.example.inventoryservice.repository;

import com.example.inventoryservice.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    // Методы для поиска сотрудников по имени, email или телефону
}
