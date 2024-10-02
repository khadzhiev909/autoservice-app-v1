package com.example.inventoryservice.controller;

import com.example.inventoryservice.entity.Supply;
import com.example.inventoryservice.service.SupplyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/supplies")
public class SupplyController {

    private final SupplyService supplyService;

    @Autowired
    public SupplyController(SupplyService supplyService) {
        this.supplyService = supplyService;
    }

    @GetMapping
    public List<Supply> getAllSupplies() {
        return supplyService.getAllSupplies();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Supply> getSupplyById(@PathVariable Long id) {
        Optional<Supply> supply = supplyService.getSupplyById(id);
        return supply.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public Supply createSupply(@RequestBody Supply supply) {
        return supplyService.saveSupply(supply);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Supply> updateSupply(@PathVariable Long id, @RequestBody Supply supplyDetails) {
        Optional<Supply> supply = supplyService.getSupplyById(id);
        if (supply.isPresent()) {
            Supply updatedSupply = supply.get();
            updatedSupply.setSupplier(supplyDetails.getSupplier());
            updatedSupply.setSupplyPrice(supplyDetails.getSupplyPrice());
            updatedSupply.setSupplyDate(supplyDetails.getSupplyDate());
            return ResponseEntity.ok(supplyService.saveSupply(updatedSupply));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSupply(@PathVariable Long id) {
        supplyService.deleteSupply(id);
        return ResponseEntity.noContent().build();
    }
}
