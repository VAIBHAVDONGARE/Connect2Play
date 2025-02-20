package com.connect2play.entities;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;
import jakarta.persistence.Version;
import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "turfs")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Turf {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "turf_Id", unique = true, nullable = false)
	private Long id;

	@NotNull(message = "Owner is required")
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "owner_id", nullable = false)
	private User owner;

	@CreationTimestamp
	@Column(nullable = false, updatable = false)
	private LocalDateTime createdAt;

	@UpdateTimestamp
	@Column(nullable = false)
	private LocalDateTime updatedAt;

	@NotBlank(message = "Turf name is required")
	@Size(min = 3, max = 100, message = "Turf name must be between 3 and 100 characters")
	@Column(name = "turf_name", nullable = false)
	private String turfName;

	@NotBlank(message = "Street address is required")
	@Size(max = 200, message = "Street address must not exceed 200 characters")
	@Column(name = "street_address", nullable = false)
	private String streetAddress;

	@NotBlank(message = "City is required")
	@Size(min = 2, max = 100, message = "City must be between 2 and 100 characters")
	@Column(nullable = false)
	private String city;

	@NotBlank(message = "State is required")
	@Size(min = 2, max = 100, message = "State must be between 2 and 100 characters")
	@Column(nullable = false)
	private String state;

	@NotBlank(message = "Pincode is required")
	@Pattern(regexp = "^[0-9]{6}$", message = "Pincode must be 6 digits")
	@Column(nullable = false)
	private String pincode;

	@NotBlank(message = "Country is required")
	@Size(min = 2, max = 100, message = "Country must be between 2 and 100 characters")
	@Column(nullable = false)
	private String country;

	@NotNull(message = "Sport type is required")
	@Enumerated(EnumType.STRING)
	@Column(name = "sports_type", nullable = false)
	private Sports sportType;

	@DecimalMin(value = "-90.0", message = "Latitude must be between -90 and 90")
	@DecimalMax(value = "90.0", message = "Latitude must be between -90 and 90")
	private Double latitude;

	@DecimalMin(value = "-180.0", message = "Longitude must be between -180 and 180")
	@DecimalMax(value = "180.0", message = "Longitude must be between -180 and 180")
	private Double longitude;

	@OneToMany(mappedBy = "turf", cascade = { CascadeType.PERSIST, CascadeType.MERGE })
	private List<Booking> bookings = new ArrayList<>();

	// @NotNull(message = "Opening time is required")
	@Column(name = "opening_time")
	private LocalTime openingTime;

	// @NotNull(message = "Closing time is required")
	@Column(name = "closing_time")
	private LocalTime closingTime;

	@DecimalMin(value = "0.0", message = "Price per hour must be positive")
	@Column(name = "price_per_hour")
	private Double pricePerHour;

	@Size(max = 500, message = "Facilities description must not exceed 500 characters")
	@Column(length = 500)
	private String facilities;

	@DecimalMin(value = "0.0", message = "Width must be positive")
	@Column(name = "width")
	private Double width;

	@DecimalMin(value = "0.0", message = "Height must be positive")
	@Column(name = "height")
	private Double height;

	@DecimalMin(value = "0.0", message = "Length must be positive")
	@Column(name = "length")
	private Double length;

	@Column(name = "is_active")
	private boolean active = true;

	@OneToMany(mappedBy = "turf", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<TurfImage> images = new ArrayList<>();

	@PrePersist
	@PreUpdate
	public void validateOpeningAndClosingTimes() {
		if (this.openingTime != null && this.closingTime != null && this.openingTime.isAfter(this.closingTime)) {
			throw new IllegalStateException("Closing time must be after opening time.");
		}
	}

	@Version
	@Column(name = "version")
	private Long version;

}