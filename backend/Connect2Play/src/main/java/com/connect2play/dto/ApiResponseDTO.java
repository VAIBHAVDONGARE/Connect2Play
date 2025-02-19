package com.connect2play.dto;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ApiResponseDTO<T> {
    
    private Boolean success;
    private String message;
    private LocalDateTime timeStamp;
    private T data;

    // Constructor for responses without data
    public ApiResponseDTO(Boolean success, String message, LocalDateTime timeStamp) {
        this.success = success;
        this.message = message;
        this.timeStamp = timeStamp;
    }

    // Constructor for responses with data
    public ApiResponseDTO(Boolean success, String message, T data) {
        this.success = success;
        this.message = message;
        this.timeStamp = LocalDateTime.now(); // Auto-generate timestamp
        this.data = data;
    }

    // Constructor for responses with only a message
    public ApiResponseDTO(String message) {
        this.success = false; // Defaulting to failure unless specified
        this.message = message;
        this.timeStamp = LocalDateTime.now();
    }
}
