package com.connect2play.dto;

import java.util.List;

import com.connect2play.entities.Gender;
import com.connect2play.entities.UserRole;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserDetailsResponseDTO extends BaseDTO {

	private String fullName;
	private String email;
	private String phNumber;
	private String bio;
	private String profileImageUrl;
	private Gender gender;
	private UserRole role;
    private List<NotificationResponseDTO> notifications;  // List of notifications for the user
    List<TurfResponseDTO> turfs;
    
    private List<TurfBookingHistoryDTO> bookingHistory;
    private double totalEarnings;
    private List<BookingRequestDTO> bookingRequests;
    private List<TeamSummaryDTO> teamsCreated;
    private List<FriendRequestResponseDTO> receivedFriendRequests;

}
