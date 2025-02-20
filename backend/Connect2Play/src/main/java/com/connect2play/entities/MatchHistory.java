package com.connect2play.entities;

import java.time.LocalDateTime;
import java.util.Map;

import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
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
import jakarta.persistence.MapKeyColumn;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import jakarta.persistence.Version;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "match_histories", indexes = {
    @Index(name = "idx_team1_id", columnList = "team1_id"),
    @Index(name = "idx_team2_id", columnList = "team2_id"),
    @Index(name = "idx_match_date", columnList = "match_date")
})
@Getter
@Setter
public class MatchHistory  {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "match_history_id")
	private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "team1_id", nullable = false)
    private Team team1;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "team2_id", nullable = false)
    private Team team2;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "winner_id") // Can be null if the match is a draw
    private Team winner;

    // Results and Score Tracking
    @Enumerated(EnumType.STRING)
    private MatchResult result; // WIN, LOSS, DRAW, ABANDONED

    @ElementCollection
    @CollectionTable(name = "match_scores", joinColumns = @JoinColumn(name = "match_id"))
    @MapKeyColumn(name = "score_type") // Example: "goals", "runs", "points"
    @Column(name = "score_value")
    private Map<String, Integer> scores; // Stores scores dynamically

 
    @Column(columnDefinition = "TEXT")
    private String matchDetails;
    
    @Enumerated(EnumType.STRING)
    @Column(name = "sport_type", nullable = false, length = 20)
    private Sports sportType;
   
    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;
    
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @Column(name = "match_date")
    private LocalDateTime matchDate;

    @Version
    private Long version; // Optimistic locking
    
    @PrePersist
    private void prePersist() {
        this.createdAt = LocalDateTime.now();
        this.updatedAt = this.createdAt;
        if (team1 != null && team2 != null && team1.equals(team2)) {
            throw new IllegalArgumentException("A team cannot play against itself.");
        }
    }
}





   
  

   

