package com.example.newpartssertvice.service;

import com.example.newpartssertvice.entity.SupplierPart;
import com.example.newpartssertvice.repository.SupplierPartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SupplierPartService {

    private final SupplierPartRepository supplierPartRepository;

    @Autowired
    public SupplierPartService(SupplierPartRepository supplierPartRepository) {
        this.supplierPartRepository = supplierPartRepository;
    }

    public List<SupplierPart> getSupplierPartsByPartId(Long partId) {
        return supplierPartRepository.findByPartId(partId);
    }

    public SupplierPart saveSupplierPart(SupplierPart supplierPart) {
        return supplierPartRepository.save(supplierPart);
    }

    public Optional<SupplierPart> getSupplierPartById(Long id) {
        return supplierPartRepository.findById(id);
    }

    public void deleteSupplierPart(Long id) {
        supplierPartRepository.deleteById(id);
    }
}
