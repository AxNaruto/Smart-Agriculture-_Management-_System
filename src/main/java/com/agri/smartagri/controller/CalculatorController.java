package com.agri.smartagri.controller;

import com.agri.smartagri.dto.FertilizerRequest;
import com.agri.smartagri.dto.FertilizerResponse;
import com.agri.smartagri.dto.WaterRequest;
import com.agri.smartagri.dto.WaterResponse;
import com.agri.smartagri.service.CalculatorService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/calculate")
@CrossOrigin(origins = "*")
public class CalculatorController {

    private final CalculatorService calculatorService;

    public CalculatorController(CalculatorService calculatorService) {
        this.calculatorService = calculatorService;
    }

    @GetMapping("/crops")
    public ResponseEntity<java.util.List<com.agri.smartagri.model.Crop>> getAllCrops() {
        return ResponseEntity.ok(calculatorService.getAllCrops());
    }

    @PostMapping("/fertilizer")
    public ResponseEntity<FertilizerResponse> calculateFertilizer(@Valid @RequestBody FertilizerRequest request) {
        FertilizerResponse response = calculatorService.calculateFertilizer(request);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/water")
    public ResponseEntity<WaterResponse> calculateWater(@Valid @RequestBody WaterRequest request) {
        WaterResponse response = calculatorService.calculateWater(request);
        return ResponseEntity.ok(response);
    }
}
