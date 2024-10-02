package com.example.inventoryservice.service;

import com.example.inventoryservice.entity.Supply;
import com.example.inventoryservice.repository.SupplyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class SupplyService {

    private final SupplyRepository supplyRepository;

    @Autowired
    public SupplyService(SupplyRepository supplyRepository) {
        this.supplyRepository = supplyRepository;
    }

    public List<Supply> getAllSupplies() {
        return supplyRepository.findAll();
    }

    public Optional<Supply> getSupplyById(Long id) {
        return supplyRepository.findById(id);
    }

    public Supply saveSupply(Supply supply) {
        return supplyRepository.save(supply);
    }

    public void deleteSupply(Long id) {
        supplyRepository.deleteById(id);
    }
}
