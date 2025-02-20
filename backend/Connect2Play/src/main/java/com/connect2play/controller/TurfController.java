package com.connect2play.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.connect2play.dto.TurfImageDTO;
import com.connect2play.dto.TurfRegistrationDTO;
import com.connect2play.dto.TurfResponseDTO;
import com.connect2play.dto.TurfUpdateDTO;
import com.connect2play.service.ITurfService;

@RestController
@RequestMapping("/api/turfs")
public class TurfController {
    
    @Autowired
    private ITurfService turfService;

    // 🔹 Register a new Turf
    @PostMapping("/register/{ownerId}")
    public ResponseEntity<TurfResponseDTO> registerTurf(@RequestBody TurfRegistrationDTO turfRegistrationDTO, 
                                                         @PathVariable Long ownerId) {
        return ResponseEntity.ok(turfService.registerTurf(turfRegistrationDTO, ownerId));
    }

    // 🔹 Update Turf Details
    @PutMapping("/update/{turfId}")
    public ResponseEntity<TurfResponseDTO> updateTurf(@PathVariable Long turfId, @RequestBody TurfUpdateDTO turfUpdateDTO) {
        return ResponseEntity.ok(turfService.updateTurf(turfId, turfUpdateDTO));
    }

    // 🔹 Delete a Turf
    @DeleteMapping("/delete/{turfId}")
    public ResponseEntity<String> deleteTurf(@PathVariable Long turfId) {
        turfService.deleteTurf(turfId);
        return ResponseEntity.ok("Turf deleted successfully");
    }

    // 🔹 Get Turf by ID
    @GetMapping("/{turfId}")
    public ResponseEntity<Optional<TurfResponseDTO>> getTurfById(@PathVariable Long turfId) {
        return ResponseEntity.ok(turfService.getTurfById(turfId));
    }

    // 🔹 Get all active turfs
    @GetMapping("/active")
    public ResponseEntity<List<TurfResponseDTO>> getAllActiveTurfs() {
        return ResponseEntity.ok(turfService.getAllActiveTurfs());
    }

    // 🔹 Search and Filter Endpoints
    @GetMapping("/city/{city}")
    public ResponseEntity<List<TurfResponseDTO>> getTurfsByCity(@PathVariable String city) {
        return ResponseEntity.ok(turfService.getTurfsByCity(city));
    }

    @GetMapping("/state/{state}")
    public ResponseEntity<List<TurfResponseDTO>> getTurfsByState(@PathVariable String state) {
        return ResponseEntity.ok(turfService.getTurfsByState(state));
    }

    @GetMapping("/sport/{sportType}")
    public ResponseEntity<List<TurfResponseDTO>> getTurfsBySport(@PathVariable String sportType) {
        return ResponseEntity.ok(turfService.getTurfsBySportType(sportType));
    }

    @GetMapping("/price-range")
    public ResponseEntity<List<TurfResponseDTO>> getTurfsByPriceRange(@RequestParam Double minPrice, @RequestParam Double maxPrice) {
        return ResponseEntity.ok(turfService.getTurfsByPriceRange(minPrice, maxPrice));
    }

    // 🔹 Upload and Retrieve Turf Images
    @PostMapping("/{turfId}/upload-images")
    public ResponseEntity<String> uploadTurfImages(@PathVariable Long turfId, @RequestParam List<MultipartFile> images) {
        turfService.uploadTurfImages(turfId, images);
        return ResponseEntity.ok("Images uploaded successfully");
    }

    @GetMapping("/{turfId}/images")
    public ResponseEntity<List<TurfImageDTO>> getTurfImages(@PathVariable Long turfId) {
        return ResponseEntity.ok(turfService.getTurfImages(turfId));
    }

//    // 🔹 Retrieve Turf Payments
//    @GetMapping("/{turfId}/payments")
//    public ResponseEntity<List<PaymentDTO>> getTurfPayments(@PathVariable Long turfId) {
//        return ResponseEntity.ok(turfService.getTurfPayments(turfId));
//    }
}
