package com.connect2play.service;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.connect2play.entities.Challenge;
import com.connect2play.entities.RequestStatus;
import com.connect2play.entities.Team;
import com.connect2play.exception.BadRequestException;
import com.connect2play.exception.ResourceNotFoundException;
import com.connect2play.repository.IChallengeRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Transactional
@RequiredArgsConstructor // Automatically injects dependencies via constructor
@Slf4j // Logging for debugging
public class ChallengeServiceImpl implements IChallengeService{

	@Autowired
    private  IChallengeRepository challengeRepository;

    
    public List<Challenge> getChallengesSentByTeam(Long challengerId) {
        log.info("Fetching challenges sent by team ID: {}", challengerId);
        return challengeRepository.findByChallengerId(challengerId);
    }

    
    public List<Challenge> getChallengesReceivedByTeam(Long opponentId) {
        log.info("Fetching challenges received by team ID: {}", opponentId);
        return challengeRepository.findByOpponentId(opponentId);
    }

    
    public Challenge getChallengeById(Long challengeId) {
        log.info("Fetching challenge by ID: {}", challengeId);
        return challengeRepository.findById(challengeId)
                .orElseThrow(() -> new ResourceNotFoundException("Challenge not found with ID: " + challengeId));
    }

    public List<Challenge> getUserChallenges(Long userId) {
        log.info("Fetching all challenges where user ID: {} is involved", userId);
        return challengeRepository.findAllUserChallenges(userId);
    }

   
    public List<Challenge> getChallengesByStatus(RequestStatus status) {
        log.info("Fetching challenges with status: {}", status);
        return challengeRepository.findByStatus(status);
    }

   
    @Transactional
    public Challenge createChallenge(Challenge challenge) {
        log.info("Creating a new challenge: {}", challenge);
        return challengeRepository.save(challenge);
    }

  
    @Transactional
    public void updateChallengeStatus(Long challengeId, RequestStatus newStatus) {
        log.info("Updating challenge ID: {} with status: {}", challengeId, newStatus);
        Challenge challenge = getChallengeById(challengeId);

        if (challenge.getStatus() == RequestStatus.ACCEPTED || challenge.getStatus() == RequestStatus.COMPLETED) {
            throw new BadRequestException("Cannot modify an already accepted/completed challenge");
        }

        challenge.setStatus(newStatus);
        challengeRepository.save(challenge);
    }

   
    @Transactional
    public void deleteChallenge(Long challengeId) {
        log.warn("Deleting challenge ID: {}", challengeId);
        if (!challengeRepository.existsById(challengeId)) {
            throw new ResourceNotFoundException("Challenge with ID " + challengeId + " not found");
        }
        challengeRepository.deleteChallengeById(challengeId);
    }
    
    @Override
    public Team getChallengerTeamByChallengeId(Long challengeId) {
        log.info("Fetching Challenger Team for Challenge ID: {}", challengeId);
        Challenge challenge = challengeRepository.findById(challengeId)
                .orElseThrow(() -> new ResourceNotFoundException("Challenge not found with ID: " + challengeId));
        return challenge.getChallenger();
    }
    
    @Override
    public Team getOpponentTeamByChallengeId(Long challengeId) {
        log.info("Fetching Opponent Team for Challenge ID: {}", challengeId);
        Challenge challenge = challengeRepository.findById(challengeId)
                .orElseThrow(() -> new ResourceNotFoundException("Challenge not found with ID: " + challengeId));
        return challenge.getOpponent();
    }
    public List<Challenge> getPendingChallengesByUserId(Long userId) {
        return challengeRepository.findByChallengerIdOrOpponentIdAndStatus(userId, RequestStatus.PENDING);
    }

}

