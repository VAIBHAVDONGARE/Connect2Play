package com.connect2play.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.connect2play.dto.NotificationResponseDTO;
import com.connect2play.entities.FriendRequest;
import com.connect2play.entities.NotificationType;
import com.connect2play.entities.RequestStatus;
import com.connect2play.entities.User;
import com.connect2play.repository.IFriendRequestRepository;
import com.connect2play.repository.IUserRepository;

@Service
@Transactional
public class FriendRequestServiceImpl implements IFriendRequestService {

	@Autowired
	private IFriendRequestRepository friendRequestRepository;

	@Autowired
	private IUserRepository userRepository;

	@Autowired
	private INotificationService notificationService;

	// Send a friend request
	public void sendFriendRequest(Long senderId, Long receiverId) {
		if (friendRequestRepository.findFriendRequestBetweenUsers(senderId, receiverId).isPresent()) {
			throw new RuntimeException("Friend request already exists.");
		}
		User sender = userRepository.findById(senderId).orElseThrow(() -> new RuntimeException("Sender not found"));
		User receiver = userRepository.findById(receiverId).orElseThrow(() -> new RuntimeException("Receiver not found"));

		FriendRequest request = FriendRequest.builder().sender(sender).receiver(receiver).status(RequestStatus.PENDING)
				.build();

		friendRequestRepository.save(request);

		NotificationResponseDTO notificationDTO = new NotificationResponseDTO(
			    (Long) null,  // the ID field from BaseDTO, initially null
			    NotificationType.FRIEND_REQUEST,  
			    "New Friend Request",
			    sender.getFullName() + " has sent you a friend request.",
			    senderId, 
			    receiverId,  // relatedId should be `receiverId`
			    "USER",
			    false, 
			    LocalDateTime.now(), 
			    null,  // readAt is initially null (not read yet)
			    false
			);

		notificationService.createNotification(notificationDTO);
	}

	// Accept a friend request
	public void acceptFriendRequest(Long requestId) {
		int updatedRows = friendRequestRepository.updateRequestStatus(requestId, "ACCEPTED");
		if (updatedRows == 0) {
			throw new RuntimeException("Friend request not found");
		}
	}

	// Decline a friend request
	public void declineFriendRequest(Long requestId) {
		int updatedRows = friendRequestRepository.updateRequestStatus(requestId, "DECLINED");
		if (updatedRows == 0) {
			throw new RuntimeException("Friend request not found");
		}
	}

	// Cancel a sent friend request
	public void cancelFriendRequest(Long requestId) {
		friendRequestRepository.deleteFriendRequest(requestId);
	}

	// Get all pending friend requests for a user
	public List<FriendRequest> getPendingRequests(Long userId) {
		return friendRequestRepository.findPendingRequestsForUser(userId);
	}

	// Get all accepted friends for a user
	public List<FriendRequest> getAllFriends(Long userId) {
		return friendRequestRepository.findAllFriends(userId);
	}

	public boolean areUsersFriends(Long userId1, Long userId2) {
		return friendRequestRepository.areUsersFriends(userId1, userId2);
	}
}
