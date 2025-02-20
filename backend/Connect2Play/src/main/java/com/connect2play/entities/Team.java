package com.connect2play.entities;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;
import jakarta.persistence.Version;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "teams")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Team {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "team_id")
	private Long id;

	@Column(nullable = false, updatable = false)
	private LocalDateTime createdAt;

	@Column(nullable = false)
	private LocalDateTime updatedAt;

	@PrePersist
	protected void onCreate() {
		createdAt = LocalDateTime.now();
		updatedAt = createdAt;
	}

	@PreUpdate
	protected void onUpdate() {
		updatedAt = LocalDateTime.now();
	}

	@NotBlank(message = "Team name is required")
	@Column(name = "team_name", nullable = false)
	private String teamName;

	@NotNull(message = "Sport type is required")
	@Enumerated(EnumType.STRING)
	@Column(name = "sports_type", nullable = false)
	private Sports sportType;

	@Column(length = 500)
	private String description;

	@NotNull(message = "Creator is required")
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "creator",  nullable = false,  foreignKey = @ForeignKey(name = "fk_team_creator")) // referencedColumnName = "id"
	private User creator;

	@NotNull(message = "Member count is required")
	@Min(value = 1, message = "Member count must be at least 1")
	@Column(name = "member_count", nullable = false)
	private Integer memberCount;

	@ElementCollection
	@CollectionTable(name = "team_member_names", joinColumns = @JoinColumn(name = "team_id"))
	@Column(name = "member_name")
	private List<String> members = new ArrayList<>();

	@ManyToMany
	@JoinTable(name = "team_members", joinColumns = @JoinColumn(name = "team_id"), inverseJoinColumns = @JoinColumn(name = "user_id"))
	private List<User> registeredMembers = new ArrayList<>();

	@OneToMany(mappedBy = "challenger", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Challenge> challengesSent = new ArrayList<>();

	@OneToMany(mappedBy = "opponent", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Challenge> challengesReceived = new ArrayList<>();

	@OneToMany(mappedBy = "team", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<TeamRequest> teamRequests = new ArrayList<>();

	@Column(name = "team_logo", length = 255)
	private String teamLogo;

	@Min(value = 0, message = "Total wins cannot be negative")
	@Column(name = "total_wins")
	private int totalWins;

	@Min(value = 0, message = "Total losses cannot be negative")
	@Column(name = "total_losses")
	private int totalLosses;

	@Min(value = 0, message = "Points cannot be negative")
	@Column(name = "points")
	private int points;

	@Version
	@Column(name = "version")
	private Long version;
}