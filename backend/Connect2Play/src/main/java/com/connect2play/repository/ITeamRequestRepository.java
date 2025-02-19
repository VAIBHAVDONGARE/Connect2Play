package com.connect2play.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.connect2play.entities.RequestStatus;
import com.connect2play.entities.Team;
import com.connect2play.entities.TeamRequest;
import com.connect2play.entities.User;

public interface ITeamRequestRepository extends JpaRepository<TeamRequest, Long> {
	Page<TeamRequest> findByTeam(Team team, Pageable pageable);
	
	boolean existsByRequestedUserAndTeamAndStatus(User userrequestedUser, Team team, RequestStatus status);
 
    @Query("SELECT COUNT(u) > 0 FROM Team t JOIN t.registeredMembers u WHERE t.id = :teamId AND u.id = :userId")
    boolean isUserAlreadyInTeam(@Param("teamId") Long teamId, @Param("userId") Long userId);

    @Query("SELECT tr FROM TeamRequest tr WHERE tr.team.id = :teamId AND tr.status = 'PENDING'")
    List<TeamRequest> findPendingRequestsByTeam(@Param("teamId") Long teamId);

//     all teams a user has requested to join
    @Query("SELECT tr.team FROM TeamRequest tr WHERE tr.requestedUser.id = :userId AND tr.status = 'PENDING' AND tr.requestType = 'JOIN_REQUEST'")
    List<Team> findTeamsUserRequestedToJoin(@Param("userId") Long userId);
 
    boolean existsByRequestedUserIdAndTeamIdAndStatus(Long userId, Long teamId, RequestStatus status );
}
