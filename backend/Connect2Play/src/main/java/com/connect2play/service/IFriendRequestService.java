package com.connect2play.service;

import java.util.List;

import com.connect2play.entities.FriendRequest;

public interface IFriendRequestService {

	void sendFriendRequest(Long senderId, Long receiverId);

	void acceptFriendRequest(Long requestId);

	void declineFriendRequest(Long requestId);

	void cancelFriendRequest(Long requestId);

	List<FriendRequest> getPendingRequests(Long userId);

	List<FriendRequest> getAllFriends(Long userId);
	
	boolean areUsersFriends(Long userId1, Long userId2);

}
