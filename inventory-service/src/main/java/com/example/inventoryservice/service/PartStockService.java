package com.example.inventoryservice.service;

import com.example.inventoryservice.entity.PartStock;
import com.example.inventoryservice.repository.PartStockRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class PartStockService {

    private final PartStockRepository partStockRepository;

    @Autowired
    public PartStockService(PartStockRepository partStockRepository) {
        this.partStockRepository = partStockRepository;
    }

    public List<PartStock> getAllPartStocks() {
        return partStockRepository.findAll();
    }

    public Optional<PartStock> getPartStockById(Long id) {
        return partStockRepository.findById(id);
    }

    public PartStock savePartStock(PartStock partStock) {
        return partStockRepository.save(partStock);
    }

    public void deletePartStock(Long id) {
        partStockRepository.deleteById(id);
    }
}
