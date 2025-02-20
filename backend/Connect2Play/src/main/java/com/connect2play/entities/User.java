package com.connect2play.entities;

import java.time.LocalDateTime;
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
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

//Jay Shree Ram || Jay Hanuman || SambSadaShiv || Shree Swami Samartha ||Neem Karoli Baba || 
@Entity
@Table(name = "users")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "user_id", unique = true, nullable = false)
	private Long id;

	@CreationTimestamp
	@Column(nullable = false, updatable = false)
	private LocalDateTime createdAt;

	@UpdateTimestamp
	@Column(nullable = false)
	private LocalDateTime updatedAt;

	public User(Long id) {

		this.id = id;
	}

	@NotBlank
	@Column(name = "full_name", length = 50)
	private String fullName;

	@NotBlank
	@Email
	@Column(length = 50, unique = true)
	private String email;
	@Column(nullable = false)
	private String password;

	@NotBlank(message = "Enter Phone Number")
	@Pattern(regexp = "\\d{10}", message = "Phone number must be 10 digits")
	@Column(name = "ph_number", nullable = false, length = 10)
	private String phNumber;

	private String bio;
	
	@Column(name = "profile_image_url", length = 255)
	private String profileImageUrl; // URL or file path to the profile image

	@Enumerated(EnumType.STRING)
	@Column(name = "gender", nullable = false)
	private Gender gender;

	@Enumerated(EnumType.STRING)
	@Column(length = 30)
	private UserRole role;

	@OneToMany(mappedBy = "creator", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	// @Column(name = "team_creator")
	private List<Team> teamsCreated; // Teams the user created

	@OneToMany(mappedBy = "createdBy", cascade = CascadeType.ALL)
	private List<TeamRequest> sentTeamRequests; // Sent requests to join teams

	@OneToMany(mappedBy = "receiver", cascade = CascadeType.ALL)
	private List<TeamRequest> receivedTeamRequests;

	@OneToMany(mappedBy = "receiver", cascade = CascadeType.ALL)
	@Column(name = "received_friend_request")
	private List<FriendRequest> receivedFriendRequests; // Received friend requests

	@OneToMany(mappedBy = "challengerUser", cascade = CascadeType.ALL)
	private List<Challenge> sentChallenges; // Challenges the user has sent to other teams

	@OneToMany(mappedBy = "opponentUser", cascade = CascadeType.ALL)
	private List<Challenge> receivedChallenges; // Challenges the user has received

	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
	private List<Notification> notifications; // User notifications

	@Column(name = "user_status", nullable = false,columnDefinition = "TINYINT(1)")
	private Boolean userStatus = true; // Default to active

	@Column(name = "last_login")
	private LocalDateTime lastLogin;
}
