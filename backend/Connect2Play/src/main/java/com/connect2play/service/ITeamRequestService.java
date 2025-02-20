package com.connect2play.service;

import java.util.List;

import com.connect2play.dto.TeamRequestCreateDTO;
import com.connect2play.dto.TeamRequestDTO;
import com.connect2play.dto.TeamRequestResponseDTO;

public interface ITeamRequestService {
	TeamRequestCreateDTO sendJoinRequest(Long userId, Long teamId,String responseMessage);

	TeamRequestDTO sendTeamInvite(Long creatorId, Long userId, Long teamId, String responseMessage);
	
    void acceptRequest(Long requestId,String responseMessage);
    
    void rejectRequest(Long requestId, String responseMessage);

    void cancelRequest(Long requestId);
    
    List<TeamRequestResponseDTO> getPendingRequestsForTeam(Long teamId);

    List<TeamRequestResponseDTO> getUserPendingRequests(Long userId);


}
