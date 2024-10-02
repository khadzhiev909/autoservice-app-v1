package com.example.clientservice.service;

import com.example.clientservice.entity.Customer;
import com.example.clientservice.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerService {

    private final CustomerRepository customerRepository;

    @Autowired
    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    // Сохранение нового клиента
    public Customer saveCustomer(Customer customer) {
        return customerRepository.save(customer);
    }

    // Получение всех клиентов
    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }

    // Получение клиента по ID
    public Optional<Customer> getCustomerById(Long id) {
        return customerRepository.findById(id);
    }

    // Получение клиента по электронной почте
    public Customer getCustomerByEmail(String email) {
        return customerRepository.findByEmail(email);
    }

    // Обновление клиента
    public Customer updateCustomer(Long id, Customer updatedCustomer) {
        return customerRepository.findById(id)
                .map(customer -> {
                    customer.setFirstName(updatedCustomer.getFirstName());
                    customer.setLastName(updatedCustomer.getLastName());
                    customer.setEmail(updatedCustomer.getEmail());
                    customer.setPhoneNumber(updatedCustomer.getPhoneNumber());
                    customer.setAddress(updatedCustomer.getAddress());
                    return customerRepository.save(customer);
                }).orElseThrow(() -> new RuntimeException("Клиент не найден с ID: " + id));
    }

    // Удаление клиента
    public void deleteCustomer(Long id) {
        if (!customerRepository.existsById(id)) {
            throw new RuntimeException("Клиент не найден с ID: " + id);
        }
        customerRepository.deleteById(id);
    }
}
