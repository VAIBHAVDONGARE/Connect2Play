package com.connect2play.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
//Jay Shree Ram || Jay Hanuman || SambSadaShiv || Neem Karoli Baba || Shree Swami Samrtha ||
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor

public class BaseDTO {
	//=> use this property only during serialization(skipped during de-serial)
		@JsonProperty(access = Access.READ_ONLY)
		private Long id;
	    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
		@JsonProperty(access = Access.READ_ONLY)
		private LocalDate createdOn;
	    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
		@JsonProperty(access = Access.READ_ONLY)
		private LocalDateTime updatedOn;
		
		 public BaseDTO(Long id) {
		        this.id = id;
		    }
}
