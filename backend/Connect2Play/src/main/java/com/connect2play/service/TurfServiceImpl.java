package com.connect2play.service;

import java.time.LocalTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.connect2play.dto.TurfImageDTO;
import com.connect2play.dto.TurfRegistrationDTO;
import com.connect2play.dto.TurfResponseDTO;
import com.connect2play.dto.TurfUpdateDTO;
import com.connect2play.entities.Sports;
import com.connect2play.entities.Turf;
import com.connect2play.entities.TurfImage;
import com.connect2play.entities.User;
import com.connect2play.exception.ResourceNotFoundException;
import com.connect2play.repository.IBookingRepository;
import com.connect2play.repository.IPaymentRepository;
import com.connect2play.repository.ITurfImageRepository;
import com.connect2play.repository.ITurfRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class TurfServiceImpl implements ITurfService {

    @Autowired
    private ITurfRepository turfRepository;
    
    @Autowired
    private IBookingRepository bookingRepository;
    
    @Autowired
    private ITurfImageRepository turfImageRepository;
    
    @Autowired
    IPaymentRepository paymentRepository;

    @Autowired
    private ModelMapper modelMapper; // Injecting ModelMapper

    // 🔹 Create a new Turf
    @Override
    public TurfResponseDTO registerTurf(TurfRegistrationDTO turfRegistrationDTO, Long ownerId) {
        if (turfRepository.existsByTurfName(turfRegistrationDTO.getTurfName())) {
            throw new IllegalArgumentException("Turf with this name already exists.");
        }

        Turf turf = modelMapper.map(turfRegistrationDTO, Turf.class);
        turf.setOwner(new User(ownerId)); // Assigning owner


        turf.setActive(true); // Ensure new turfs are active by default
        turf.setOpeningTime(LocalTime.parse(turfRegistrationDTO.getOpeningTime()));
        turf.setClosingTime(LocalTime.parse(turfRegistrationDTO.getClosingTime()));

        Turf savedTurf = turfRepository.save(turf);
        return modelMapper.map(savedTurf, TurfResponseDTO.class);
    }

    // 🔹 Update an existing Turf
    @Override
    public TurfResponseDTO updateTurf(Long turfId, TurfUpdateDTO turfUpdateDTO) {
        Turf turf = turfRepository.findById(turfId)
                .orElseThrow(() -> new IllegalArgumentException("Turf not found with ID: " + turfId));

        modelMapper.map(turfUpdateDTO, turf); // Mapping updated values
        turf.setOpeningTime(LocalTime.parse(turfUpdateDTO.getOpeningTime()));
        turf.setClosingTime(LocalTime.parse(turfUpdateDTO.getClosingTime()));


        Turf updatedTurf = turfRepository.save(turf);
        return modelMapper.map(updatedTurf, TurfResponseDTO.class);
    }

    // 🔹 Delete a Turf
    @Override
    public void deleteTurf(Long turfId) {
        if (!turfRepository.existsById(turfId)) {
            throw new IllegalArgumentException("Turf not found with ID: " + turfId);
        }
        turfRepository.deleteById(turfId);
    }

    // 🔹 Get Turf by ID
    @Override
    public Optional<TurfResponseDTO> getTurfById(Long turfId) {
        return turfRepository.findById(turfId).map(turf -> modelMapper.map(turf, TurfResponseDTO.class));
    }

    // 🔹 Get all active turfs
    @Override
    public List<TurfResponseDTO> getAllActiveTurfs() {
        return turfRepository.findByActiveTrue()
                .stream()
                .map(turf -> modelMapper.map(turf, TurfResponseDTO.class))
                .collect(Collectors.toList());
    }

    // 🔹 Get turfs by city
    @Override
    public List<TurfResponseDTO> getTurfsByCity(String city) {
        return turfRepository.findByCityIgnoreCaseAndActiveTrue(city)
                .stream()
                .map(turf -> modelMapper.map(turf, TurfResponseDTO.class))
                .collect(Collectors.toList());
    }

    // 🔹 Get turfs by state
    @Override
    public List<TurfResponseDTO> getTurfsByState(String state) {
        return turfRepository.findByStateIgnoreCaseAndActiveTrue(state)
                .stream()
                .map(turf -> modelMapper.map(turf, TurfResponseDTO.class))
                .collect(Collectors.toList());
    }

    // 🔹 Get turfs by sport type
    @Override
    public List<TurfResponseDTO> getTurfsBySportType(String sportType) {
        Pageable pageable = PageRequest.of(0, 10); // First page, 10 records per page

        return turfRepository.findBySportType(Sports.valueOf(sportType), pageable)
                .stream()
                .map(turf -> modelMapper.map(turf, TurfResponseDTO.class))
                .collect(Collectors.toList());
    }

    // 🔹 Get turfs owned by a specific user
    @Override
    public List<TurfResponseDTO> getTurfsByOwner(Long ownerId) {
        return turfRepository.findByOwnerIdAndActiveTrue(ownerId)
                .stream()
                .map(turf -> modelMapper.map(turf, TurfResponseDTO.class))
                .collect(Collectors.toList());
    }

    // 🔹 Get turfs within a price range
    @Override
    public List<TurfResponseDTO> getTurfsByPriceRange(Double minPrice, Double maxPrice) {
        return turfRepository.findByPricePerHourBetweenAndActiveTrue(minPrice, maxPrice)
                .stream()
                .map(turf -> modelMapper.map(turf, TurfResponseDTO.class))
                .collect(Collectors.toList());
    }

    // 🔹 Get turfs within a radius of a location
    @Override
    public List<TurfResponseDTO> getTurfsNearby(Double latitude, Double longitude) {
        return turfRepository.findTurfsWithinRadius(latitude, longitude)
                .stream()
                .map(turf -> modelMapper.map(turf, TurfResponseDTO.class))
                .collect(Collectors.toList());
    }

    // 🔹 Get turfs by keyword search (Full-text search)
    @Override
    public List<TurfResponseDTO> searchTurfs(String keyword) {
        return turfRepository.searchTurfs(keyword)
                .stream()
                .map(turf -> modelMapper.map(turf, TurfResponseDTO.class))
                .collect(Collectors.toList());
    }

    // 🔹 Get paginated list of all active turfs
    @Override
    public Page<TurfResponseDTO> getPaginatedTurfs(Pageable pageable) {
        return turfRepository.findByActiveTrue(pageable)
                .map(turf -> modelMapper.map(turf, TurfResponseDTO.class));
    }

	@Override
	public void uploadTurfImages(Long turfId, List<MultipartFile> images) {
		// TODO Auto-generated method stub
		 Turf turf = turfRepository.findById(turfId)
        .orElseThrow(() -> new ResourceNotFoundException("Turf not found with ID: " + turfId));

images.forEach(image -> {
    try {
        TurfImage turfImage = new TurfImage();
        turfImage.setTurf(turf);
        turfImage.setImageUrl("uploaded/path/" + image.getOriginalFilename()); // Placeholder for cloud storage
        turfImageRepository.save(turfImage);
    } catch (Exception e) {
        throw new RuntimeException("Failed to upload image", e);
    }
});
	}

	@Override
	public List<TurfImageDTO> getTurfImages(Long turfId) {
		// TODO Auto-generated method stub
		return turfImageRepository.findByTurfId(turfId)
                .stream()
                .map(image -> modelMapper.map(image, TurfImageDTO.class))
                .collect(Collectors.toList());
	}
	
//	@Override
//    public List<PaymentDTO> getTurfPayments(Long turfId) {
//        return paymentRepository.findByTurfId(turfId)
//                .stream()
//                .map(payment -> modelMapper.map(payment, PaymentDTO.class))
//                .collect(Collectors.toList());
//    }

	
}
