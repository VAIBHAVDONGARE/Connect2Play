package com.connect2play.entities;

public enum NotificationType {
	FRIEND_REQUEST("Friend request notifications"), SYSTEM_ALERT("System generated alerts"),
	FRIEND_INTERACTION("Friend activity notifications"), TEAM_MATCH_CHALLENGE("Team challenge notifications"),
	PROMOTIONS("Promotional notifications"), TEAM_INVITE("Team invitation notifications"),
	TEAM_JOIN_REQUEST("Team Join Request"), PAYMENT("Payment related notifications"), GENERAL("General notifications"),
	REQUEST_ACCEPTED("Request Accepted"), REQUEST_REJECTED("Request Rejected"), REQUEST_CANCELLED("Request Cancelled"),
	PENDING_REQUESTS("Pending Requests");

	private final String description;

	NotificationType(String description) {
		this.description = description;
	}

	public String getDescription() {
		return description;
	}
}
