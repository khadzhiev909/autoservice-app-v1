package com.example.clientservice.repository;

import com.example.clientservice.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {
    // Здесь можно добавить дополнительные методы поиска, если необходимо
    Customer findByEmail(String email);
}
