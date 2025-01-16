package com.example.inventoryservice.controller;

import com.example.inventoryservice.entity.Part;
import com.example.inventoryservice.service.PartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/parts")
public class PartController {

    private final PartService partService;
    @Autowired
    public PartController(PartService partService) {
        this.partService = partService;
    }

//    @GetMapping
//    public List<Part> getAllParts() {
//        return partService.getAllParts();
//    }

//    @GetMapping("/{id}")
//    public ResponseEntity<Part> getPartById(@PathVariable Long id) {
//        Optional<Part> part = partService.getPartById(id);
//        return part.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
//    }
//
//    @PostMapping
//    public Part createPart(@RequestBody Part part) {
//        return partService.savePart(part);
//    }
//
//    @PutMapping("/{id}")
//    public ResponseEntity<Part> updatePart(@PathVariable Long id, @RequestBody Part partDetails) {
//        Optional<Part> part = partService.getPartById(id);
//        if (part.isPresent()) {
//            Part updatedPart = part.get();
//            updatedPart.setName(partDetails.getName());
//            updatedPart.setCategory(partDetails.getCategory());
//            updatedPart.setPrice(partDetails.getPrice());
//            return ResponseEntity.ok(partService.savePart(updatedPart));
//        } else {
//            return ResponseEntity.notFound().build();
//        }
//    }
//
//    @DeleteMapping("/{id}")
//    public ResponseEntity<Void> deletePart(@PathVariable Long id) {
//        partService.deletePart(id);
//        return ResponseEntity.noContent().build();
//    }

    @GetMapping
    public ResponseEntity<Page<Part>> getAllPart(
            @RequestParam(defaultValue = "0") int page,//номер страницы
            @RequestParam(defaultValue = "10") int size,//Количество записей на страницу
            @RequestParam(defaultValue = "id") String sortBy,//По какому полю будем сортировать
    @RequestParam(defaultValue = "desc") String direction//В каком направлении
    ) {
        Sort sort = direction.equalsIgnoreCase("desc") ? Sort.by(sortBy).descending() : Sort.by(sortBy).ascending();
        Pageable pageable = PageRequest.of(page, size, sort);//Это объект, который описывает какую страницу  и сколько эл. мы хотим получить
        Page<Part> parts = partService.getAllParts(pageable);
        return ResponseEntity.ok(parts);
    }
}
