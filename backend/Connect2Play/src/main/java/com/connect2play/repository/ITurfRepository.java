package com.connect2play.repository;

import com.connect2play.entities.Sports;
import com.connect2play.entities.Turf;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ITurfRepository extends JpaRepository<Turf, Long> {

    // 🔹 Fetch all active turfs
    List<Turf> findByActiveTrue();

    // 🔹 Fetch all turfs owned by a specific user
    List<Turf> findByOwnerIdAndActiveTrue(Long ownerId);

    // 🔹 Search by city (Case-Insensitive)
    List<Turf> findByCityIgnoreCaseAndActiveTrue(String city);

    // 🔹 Search by state (Case-Insensitive)
    List<Turf> findByStateIgnoreCaseAndActiveTrue(String state);

    // 🔹 Find a turf by name (Case-Insensitive)
    List<Turf> findByTurfNameIgnoreCase(String turfName);

    // 🔹 Search turfs within a price range
    List<Turf> findByPricePerHourBetweenAndActiveTrue(Double minPrice, Double maxPrice);

    // 🔹 Search Turf by Pincode
    List<Turf> findByPincodeAndActiveTrue(String pincode);

    // 🔹 Check if a turf with a given name already exists
    boolean existsByTurfName(String turfName);

    // 🔹 Find turfs with open booking slots
    @Query("SELECT DISTINCT t FROM Turf t JOIN t.bookings b WHERE b.status = 'AVAILABLE'")
    List<Turf> findTurfsWithAvailableBookings();

    // 🔹 Full-text search: Turf name, city, state, and facilities
    @Query("SELECT t FROM Turf t WHERE t.active = true AND " +
           "(LOWER(t.turfName) LIKE LOWER(CONCAT('%', :keyword, '%')) " +
           "OR LOWER(t.city) LIKE LOWER(CONCAT('%', :keyword, '%')) " +
           "OR LOWER(t.state) LIKE LOWER(CONCAT('%', :keyword, '%')) " +
           "OR LOWER(t.facilities) LIKE LOWER(CONCAT('%', :keyword, '%')))")
    List<Turf> searchTurfs(@Param("keyword") String keyword);

    // 🔹 Find turfs by sport type
    Page<Turf> findBySportType(Sports sportType, Pageable pageable);

    // 🔹 Find available turfs within a price range and sport type
    @Query("SELECT t FROM Turf t WHERE t.active = true AND " +
           "(t.pricePerHour BETWEEN :minPrice AND :maxPrice) AND t.sportType = :sportType")
    List<Turf> filterTurfs(@Param("minPrice") Double minPrice,
                           @Param("maxPrice") Double maxPrice,
                           @Param("sportType") Sports sportType);

    // 🔹 Find nearby turfs using latitude & longitude (Approximate method)
//    @Query("SELECT t FROM Turf t WHERE t.active = true AND " +
//           "ABS(t.latitude - :latitude) <= 0.1 AND ABS(t.longitude - :longitude) <= 0.1")
//    List<Turf> findNearbyTurfs(@Param("latitude") Double latitude,
//                               @Param("longitude") Double longitude);

    // 🔹 Find nearby turfs within a 10km radius (More precise Haversine formula)
    @Query("SELECT t FROM Turf t WHERE t.active = true AND " +
           "(6371 * acos(cos(radians(:latitude)) * cos(radians(t.latitude)) " +
           "* cos(radians(t.longitude) - radians(:longitude)) + sin(radians(:latitude)) " +
           "* sin(radians(t.latitude)))) <= 10")
    List<Turf> findTurfsWithinRadius(@Param("latitude") Double latitude,
                                     @Param("longitude") Double longitude);

    // 🔹 Paginated search for all active turfs
    Page<Turf> findByActiveTrue(Pageable pageable);
}
