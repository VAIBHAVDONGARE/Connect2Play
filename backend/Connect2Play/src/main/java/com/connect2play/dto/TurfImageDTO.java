package com.connect2play.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * DTO for representing image metadata for a Turf.
 * This class is used for handling image URL and related data.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TurfImageDTO {

    private String imageUrl;       // URL of the image (e.g., image stored on a cloud server or CDN)

    private String imageType;      // Type of image (e.g., "thumbnail", "full-size")

    private String caption;        // Optional caption or description for the image
}
