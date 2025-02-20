package com.connect2play.service;

import java.util.List;

import com.connect2play.entities.Challenge;
import com.connect2play.entities.RequestStatus;
import com.connect2play.entities.Team;

public interface IChallengeService {
	List<Challenge> getChallengesSentByTeam(Long challengerId);

	List<Challenge> getChallengesReceivedByTeam(Long opponentId);

	Challenge getChallengeById(Long challengeId);

	List<Challenge> getUserChallenges(Long userId);

	List<Challenge> getChallengesByStatus(RequestStatus status);

	Challenge createChallenge(Challenge challenge);

	void updateChallengeStatus(Long challengeId, RequestStatus newStatus);

	void deleteChallenge(Long challengeId);

	Team getChallengerTeamByChallengeId(Long challengeId);

	Team getOpponentTeamByChallengeId(Long challengeId);
	
	List<Challenge> getPendingChallengesByUserId(Long userId);

}
