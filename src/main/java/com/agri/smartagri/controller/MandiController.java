package com.agri.smartagri.controller;

import com.agri.smartagri.model.MandiListing;
import com.agri.smartagri.service.MandiService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/mandi")
@CrossOrigin(origins = "*")
public class MandiController {

    private final MandiService mandiService;

    public MandiController(MandiService mandiService) {
        this.mandiService = mandiService;
    }

    @GetMapping
    public ResponseEntity<List<MandiListing>> getAllListings() {
        List<MandiListing> listings = mandiService.getAllListings();
        return ResponseEntity.ok(listings);
    }

    @PostMapping
    public ResponseEntity<MandiListing> createListing(@RequestBody MandiListing listing) {
        MandiListing saved = mandiService.createListing(listing);
        return new ResponseEntity<>(saved, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, Object>> deleteListing(@PathVariable Long id) {
        boolean deleted = mandiService.deleteListing(id);
        Map<String, Object> response = new HashMap<>();
        if (deleted) {
            response.put("success", true);
            response.put("message", "Listing with ID " + id + " deleted successfully.");
            return ResponseEntity.ok(response);
        } else {
            response.put("success", false);
            response.put("message", "Listing with ID " + id + " not found.");
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }
    }
}
