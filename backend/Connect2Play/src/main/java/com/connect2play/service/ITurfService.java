package com.connect2play.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

import com.connect2play.dto.TurfImageDTO;
import com.connect2play.dto.TurfRegistrationDTO;
import com.connect2play.dto.TurfResponseDTO;
import com.connect2play.dto.TurfUpdateDTO;

public interface ITurfService {

    // 🔹 Create a new Turf
    TurfResponseDTO registerTurf(TurfRegistrationDTO turfRegistrationDTO, Long ownerId);

    // 🔹 Update an existing Turf
    TurfResponseDTO updateTurf(Long turfId, TurfUpdateDTO turfUpdateDTO);

    // 🔹 Delete a Turf
    void deleteTurf(Long turfId);

    // 🔹 Get Turf by ID
    Optional<TurfResponseDTO> getTurfById(Long turfId);

    // 🔹 Get all active turfs
    List<TurfResponseDTO> getAllActiveTurfs();

    // 🔹 Get turfs by city
    List<TurfResponseDTO> getTurfsByCity(String city);

    // 🔹 Get turfs by state
    List<TurfResponseDTO> getTurfsByState(String state);

    // 🔹 Get turfs by sport type
    List<TurfResponseDTO> getTurfsBySportType(String sportType);

    // 🔹 Get turfs owned by a specific user
    List<TurfResponseDTO> getTurfsByOwner(Long ownerId);

    // 🔹 Get turfs within a price range
    List<TurfResponseDTO> getTurfsByPriceRange(Double minPrice, Double maxPrice);

    // 🔹 Get turfs within a radius of a location
    List<TurfResponseDTO> getTurfsNearby(Double latitude, Double longitude);

    // 🔹 Get turfs by keyword search (Full-text search)
    List<TurfResponseDTO> searchTurfs(String keyword);

    // 🔹 Get paginated list of all active turfs
    Page<TurfResponseDTO> getPaginatedTurfs(Pageable pageable);
    
    void uploadTurfImages(Long turfId, List<MultipartFile> images);
    List<TurfImageDTO> getTurfImages(Long turfId);
    
    //public List<PaymentDTO> getTurfPayments(Long turfId);

}
