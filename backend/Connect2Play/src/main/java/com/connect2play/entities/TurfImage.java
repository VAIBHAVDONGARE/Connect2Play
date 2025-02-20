package com.connect2play.entities;

import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "turf_images", indexes = {
	    @Index(name = "idx_turf_image_turf", columnList = "turf_id")
	})@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TurfImage {
	 @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	 @Column(name = "turf_image_id", unique = true, nullable = false)
	    private Long turfImageId;

	 @Column(nullable = false, updatable = false)
	 private LocalDateTime createdAt;

	 @CreationTimestamp
	 @Column(name = "uploaded_at", updatable = false)
	 private LocalDateTime uploadedAt;

	
    @NotBlank(message = "Image URL is required")
    @Column(name = "image_url", nullable = false)
    private String imageUrl;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "turf_id", nullable = false)
    private Turf turf;
    
    @Column(name = "display_order", nullable = false)
    private int displayOrder; // To keep images in order

    @Column(name = "is_primary", nullable = false)
    private boolean isPrimary; // o mark the main image

   
}
