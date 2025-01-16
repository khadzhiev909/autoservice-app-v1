package com.example.clientservice.service;

import com.example.clientservice.dto.AppointmentDTO;
import com.example.clientservice.entity.Appointment;
import com.example.clientservice.entity.Customer;
import com.example.clientservice.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

@Service
public class CustomerService {
    private final Logger logger = Logger.getLogger("CustomerService");

    private final CustomerRepository customerRepository;
    private final KafkaTemplate<String, AppointmentDTO> kafkaProducer;

    @Autowired
    public CustomerService(CustomerRepository customerRepository, KafkaTemplate<String, AppointmentDTO> kafkaProducer) {
        this.customerRepository = customerRepository;
        this.kafkaProducer = kafkaProducer;
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
