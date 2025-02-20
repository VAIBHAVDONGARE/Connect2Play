package com.connect2play.entities;

import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;
import jakarta.persistence.Version;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "challenges", indexes = { @Index(name = "idx_challenge_challenger", columnList = "challenger_team_id"),
		@Index(name = "idx_challenge_opponent", columnList = "opponent_team_id"),
		@Index(name = "idx_challenge_status", columnList = "status"),
		@Index(name = "idx_challenge_scheduled_time", columnList = "scheduled_time") })
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Challenge {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "challenger_id")
	private Long id;

	@CreationTimestamp
	@Column(nullable = false, updatable = false)
	private LocalDateTime createdAt;

	@UpdateTimestamp
	@Column(nullable = false)
	private LocalDateTime updatedAt;


	@NotNull(message = "Challenger team is required")
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "challenger_team_id", nullable = false, foreignKey = @ForeignKey(name = "fk_challenge_challenger_team"))
	private Team challenger;

	@NotNull(message = "Opponent team is required")
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "opponent_team_id", nullable = false, foreignKey = @ForeignKey(name = "fk_challenge_opponent_team"))
	private Team opponent;

	@ManyToOne
	@JoinColumn(name = "opponent_user_id") // Ensure this matches the column name in DB
	private User opponentUser;

	@ManyToOne
	@JoinColumn(name = "challenger_user_id") // Ensure this matches the column name in DB
	private User challengerUser;

	@NotNull(message = "Challenge status is required")
	@Enumerated(EnumType.STRING)
	@Column(name = "status", nullable = false, length = 20)
	private RequestStatus status;

	@Future(message = "Scheduled time must be in the future")
	@Column(name = "scheduled_time")
	private LocalDateTime scheduledTime;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "turf_id", foreignKey = @ForeignKey(name = "fk_challenge_turf"))
	private Turf turf;

	@Size(max = 500, message = "Challenge message must not exceed 500 characters")
	@Column(name = "challenge_message", length = 500)
	private String challengeMessage;

	@Future
	@Column(name = "expiry_time")
	private LocalDateTime expiryTime;

	@Column(name = "response_time")
	private LocalDateTime responseTime;

	@Size(max = 500, message = "Response message must not exceed 500 characters")
	@Column(name = "response_message", length = 500)
	private String responseMessage;

	@Column(name = "is_cancelled", nullable = false)
	private boolean cancelled = false;

	@Size(max = 500, message = "Cancellation reason must not exceed 500 characters")
	@Column(name = "cancellation_reason", length = 500)
	private String cancellationReason;

	@Column(name = "reminder_sent", nullable = false)
	private boolean reminderSent = false;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "booking_id", foreignKey = @ForeignKey(name = "fk_challenge_booking"))
	private Booking booking;

	public void cancelChallenge(String reason) {
		this.cancelled = true;
		this.cancellationReason = reason;
		this.status = RequestStatus.CANCELLED; // Assuming you want to change the status too
	}

	@Version
	private Long version;
}
