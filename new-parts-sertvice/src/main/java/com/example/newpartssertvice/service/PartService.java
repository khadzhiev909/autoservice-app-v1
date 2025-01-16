package com.example.newpartssertvice.service;

import com.example.newpartssertvice.converter.PartConverter;
import com.example.newpartssertvice.entity.Part;
import com.example.newpartssertvice.repository.PartRepository;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;
import java.util.concurrent.CompletableFuture;

@Service
public class PartService {
//    private static final String TOPIC = "parts-search-topic";
//    private final KafkaTemplate<String, Part> kafkaTemplate;
    private final RestTemplate restTemplate;
    private final PartRepository partRepository;
//
    @Autowired
    public PartService(RestTemplate restTemplate, PartRepository partRepository) {
        this.restTemplate = restTemplate;
//        this.kafkaTemplate = kafkaTemplate; KafkaTemplate<String, Object> kafkaTemplate,
        this.partRepository = partRepository;
    }

    public Optional<Part> getPartById(Long id) {
        return partRepository.findById(id);
    }

    public Optional<Part> getPartByPartNumber(String partNumber) {
        return partRepository.findByPartNumber(partNumber);
    }

    public Part savePart(Part part) {
        return partRepository.save(part);
    }

    public void deletePart(Long id) {
        partRepository.deleteById(id);
    }



    @Retry(name = "apiRetry") // Повторные попытки
    @CircuitBreaker(name = "apiCircuitBreaker") // Circuit Breaker
    public CompletableFuture<Part> fetchPartFromSupplier(String apiUrl, PartConverter converter) {
        return CompletableFuture.supplyAsync(() -> {
            String response = restTemplate.getForObject(apiUrl, String.class);
            // Преобразование JSON в объект Part с использованием конвертера
            JSONObject jsonObject = new JSONObject(response);
            return converter.convert(jsonObject); // Пример для первого поставщика
        });
    }


}
