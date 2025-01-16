package com.example.newpartssertvice.controller;

import com.example.newpartssertvice.entity.SupplierPart;
import com.example.newpartssertvice.service.SupplierPartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/supplier-parts")
public class SupplierPartController {

    private final SupplierPartService supplierPartService;

    @Autowired
    public SupplierPartController(SupplierPartService supplierPartService) {
        this.supplierPartService = supplierPartService;
    }

    @GetMapping("/part/{partId}")
    public ResponseEntity<List<SupplierPart>> getSupplierPartsByPartId(@PathVariable Long partId) {
        List<SupplierPart> supplierParts = supplierPartService.getSupplierPartsByPartId(partId);
        return ResponseEntity.ok(supplierParts);
    }

    @PostMapping
    public ResponseEntity<SupplierPart> createSupplierPart(@RequestBody SupplierPart supplierPart) {
        SupplierPart savedSupplierPart = supplierPartService.saveSupplierPart(supplierPart);
        return ResponseEntity.ok(savedSupplierPart);
    }

    @GetMapping("/{id}")
    public ResponseEntity<SupplierPart> getSupplierPartById(@PathVariable Long id) {
        Optional<SupplierPart> supplierPart = supplierPartService.getSupplierPartById(id);
        return supplierPart.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSupplierPart(@PathVariable Long id) {
        supplierPartService.deleteSupplierPart(id);
        return ResponseEntity.noContent().build();
    }
}
