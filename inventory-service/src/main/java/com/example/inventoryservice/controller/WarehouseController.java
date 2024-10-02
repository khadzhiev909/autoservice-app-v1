package com.example.inventoryservice.controller;

import com.example.inventoryservice.entity.Warehouse;
import com.example.inventoryservice.service.WarehouseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/warehouses")
public class WarehouseController {

    private final WarehouseService warehouseService;
    @Autowired
    public WarehouseController(WarehouseService warehouseService) {
        this.warehouseService = warehouseService;
    }

    @GetMapping
    public List<Warehouse> getAllWarehouses() {
        return warehouseService.getAllWarehouses();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Warehouse> getWarehouseById(@PathVariable Long id) {
        Optional<Warehouse> warehouse = warehouseService.getWarehouseById(id);
        return warehouse.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public Warehouse createWarehouse(@RequestBody Warehouse warehouse) {
        return warehouseService.saveWarehouse(warehouse);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Warehouse> updateWarehouse(@PathVariable Long id, @RequestBody Warehouse warehouseDetails) {
        Optional<Warehouse> warehouse = warehouseService.getWarehouseById(id);
        if (warehouse.isPresent()) {
            Warehouse updatedWarehouse = warehouse.get();
            updatedWarehouse.setName(warehouseDetails.getName());
            updatedWarehouse.setAddress(warehouseDetails.getAddress());
            updatedWarehouse.setPhone(warehouseDetails.getPhone());
            updatedWarehouse.setContactPerson(warehouseDetails.getContactPerson());
            return ResponseEntity.ok(warehouseService.saveWarehouse(updatedWarehouse));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteWarehouse(@PathVariable Long id) {
        warehouseService.deleteWarehouse(id);
        return ResponseEntity.noContent().build();
    }
}
