package com.connect2play.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import com.connect2play.entities.Gender;
import com.connect2play.entities.UserRole;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserFullDetailsResponseDTO extends BaseDTO {
	private Long id;
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@JsonProperty(access = Access.READ_ONLY)
	private LocalDate createdOn;
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@JsonProperty(access = Access.READ_ONLY)
	private LocalDateTime updatedOn;
	private String fullName;
	private String email;
	private String phNumber;
	private String bio;
	private String profileImageUrl;
	private Gender gender;
	private UserRole role;

	// keep the response lightweight.
	private List<TeamSummaryDTO> teamsCreated;
	private int totalTeamsCreatedCount; // For pagination

	private List<TeamRequestResponseDTO> receivedTeamRequests;
	private int totalReceivedTeamRequestsCount; // For pagination

	private List<FriendRequestResponseDTO> receivedFriendRequests;
	private int totalReceivedFriendRequestsCount; // For pagination

	private List<NotificationResponseDTO> notifications;
	private int totalNotificationsCount; // For pagination

	List<TurfResponseDTO> turfs;

	private List<TurfBookingHistoryDTO> bookingHistory;
	private double totalEarnings;
	private List<BookingRequestDTO> bookingRequests;

}
