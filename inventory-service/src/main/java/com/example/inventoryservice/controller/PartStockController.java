package com.example.inventoryservice.controller;

import com.example.inventoryservice.entity.PartStock;
import com.example.inventoryservice.service.PartStockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/part-stocks")
public class PartStockController {

    private final PartStockService partStockService;
    @Autowired
    public PartStockController(PartStockService partStockService) {
        this.partStockService = partStockService;
    }

    @GetMapping
    public List<PartStock> getAllPartStocks() {
        return partStockService.getAllPartStocks();
    }

    @GetMapping("/{id}")
    public ResponseEntity<PartStock> getPartStockById(@PathVariable Long id) {
        Optional<PartStock> partStock = partStockService.getPartStockById(id);
        return partStock.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public PartStock createPartStock(@RequestBody PartStock partStock) {
        return partStockService.savePartStock(partStock);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PartStock> updatePartStock(@PathVariable Long id, @RequestBody PartStock partStockDetails) {
        Optional<PartStock> partStock = partStockService.getPartStockById(id);
        if (partStock.isPresent()) {
            PartStock updatedPartStock = partStock.get();
//            updatedPartStock.setPart(partStockDetails.getPart());
            updatedPartStock.setWarehouse(partStockDetails.getWarehouse());
            updatedPartStock.setQuantity(partStockDetails.getQuantity());
            return ResponseEntity.ok(partStockService.savePartStock(updatedPartStock));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePartStock(@PathVariable Long id) {
        partStockService.deletePartStock(id);
        return ResponseEntity.noContent().build();
    }
}
