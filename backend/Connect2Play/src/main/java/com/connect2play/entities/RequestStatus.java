package com.connect2play.entities;

public enum RequestStatus {
	PENDING("Pending"), ACCEPTED("Approved"), REJECTED("Rejected"), CANCELLED("Cancelled"), COMPLETED("Completed");

	private final String displayName;

	RequestStatus(String displayName) {
		this.displayName = displayName;
	}

	public String getDisplayName() {
		return displayName;
	}
}
