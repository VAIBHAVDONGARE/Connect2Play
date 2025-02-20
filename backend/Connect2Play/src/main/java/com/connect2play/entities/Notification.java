package com.connect2play.entities;

import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "notifications", indexes = { @Index(name = "idx_notification_user", columnList = "user_id"),
		@Index(name = "idx_notification_type", columnList = "notification_type"),
		@Index(name = "idx_notification_is_read", columnList = "is_read") })
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor // Add AllArgsConstructor if you need to instantiate with all fields
public class Notification {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "notification_id")
	private Long id;

	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "user_id", nullable = false)
	private User user; // Receiver of the notification

	@Enumerated(EnumType.STRING)
	@Column(name = "notification_type", nullable = false, length = 30)
	private NotificationType type; // ENUM: FRIEND_REQUEST, SYSTEM_ALERT, MATCH_CHALLENGE, etc.

	@Size(max = 100)
	@Column(name = "title", nullable = false)
	private String title; // Short title for notification (e.g., "New Friend Request")

	@Size(max = 500)
	@Column(name = "message", nullable = false)
	private String message; // Detailed message

	@Column(name = "sender_id")
	private Long senderId; // Who sent this notification (Friend, Team, etc.)

	@Column(name = "related_id")
	private Long relatedId; // Stores related entity ID (Match ID, Team ID, etc.)

	@Column(name = "related_type", length = 50)
	private String relatedType; // Stores related entity type (MATCH, TEAM, FRIEND_REQUEST)

	@Column(name = "is_read", nullable = false, columnDefinition = "BOOLEAN DEFAULT FALSE")
	private Boolean isRead = false;

	@Column(name = "read_at")
	private LocalDateTime readAt; // Timestamp when the user read the notification

	@CreationTimestamp
	@Column(name = "sent_time", nullable = false, updatable = false)
	private LocalDateTime sentTime;

	@Column(name = "is_deleted", nullable = false, columnDefinition = "BOOLEAN DEFAULT FALSE")
	private Boolean isDeleted = false; // Soft delete / archival

	@UpdateTimestamp
	@Column(name = "last_updated_time")
	private LocalDateTime lastUpdatedTime;

	@CreationTimestamp
	@Column(name = "created_at")
	private LocalDateTime createdAt;
	public void markAsRead() {
		if (!this.isRead) {
			this.isRead = true;
			this.readAt = LocalDateTime.now();
		}
	}
}
