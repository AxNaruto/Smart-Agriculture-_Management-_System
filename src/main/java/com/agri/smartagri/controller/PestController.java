package com.agri.smartagri.controller;

import com.agri.smartagri.model.PestAdvice;
import com.agri.smartagri.service.PestService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/pest")
@CrossOrigin(origins = "*")
public class PestController {

    private final PestService pestService;

    public PestController(PestService pestService) {
        this.pestService = pestService;
    }

    @GetMapping
    public ResponseEntity<List<PestAdvice>> getPestAdvice(@RequestParam(required = false) String crop) {
        if (crop != null && !crop.trim().isEmpty()) {
            return ResponseEntity.ok(pestService.getAdviceByCrop(crop));
        }
        return ResponseEntity.ok(pestService.getAllAdvice());
    }

    @GetMapping("/search")
    public ResponseEntity<List<PestAdvice>> searchBySymptom(@RequestParam String symptom) {
        return ResponseEntity.ok(pestService.searchBySymptom(symptom));
    }

    @GetMapping("/{id}")
    public ResponseEntity<PestAdvice> getAdviceById(@PathVariable Long id) {
        return pestService.getAdviceById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}
