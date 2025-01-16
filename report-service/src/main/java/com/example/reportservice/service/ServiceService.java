package com.example.reportservice.service;

import com.example.reportservice.entity.Services;
import com.example.reportservice.repository.ServiceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class ServiceService {
    private final ServiceRepository serviceRepository;

    public List<Services> getAllServices() {
        return serviceRepository.findAll();
    }

    public Services saveService(Services service) {
        return serviceRepository.save(service);
    }
}
