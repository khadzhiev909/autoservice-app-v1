package com.example.newpartssertvice.controller;

import com.example.newpartssertvice.entity.Part;
import com.example.newpartssertvice.service.PartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/parts")
public class PartSearchController {

    private final PartService partService;

    @Autowired
    public PartSearchController(PartService partService) {
        this.partService = partService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Part> getPartById(@PathVariable Long id) {
        Optional<Part> part = partService.getPartById(id);
        return part.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/part-number/{partNumber}")
    public ResponseEntity<Part> getPartByPartNumber(@PathVariable String partNumber) {
        Optional<Part> part = partService.getPartByPartNumber(partNumber);
        return part.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Part> createPart(@RequestBody Part part) {
        Part savedPart = partService.savePart(part);
        return ResponseEntity.ok(savedPart);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePart(@PathVariable Long id) {
        partService.deletePart(id);
        return ResponseEntity.noContent().build();
    }
}
