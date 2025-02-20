package com.connect2play.entities;

public enum BookingStatus {
	PENDING("Pending"), CONFIRMED("Confirmed"), CANCELLED("Cancelled"), COMPLETED("Completed");

	private final String displayName;

	BookingStatus(String displayName) {
		this.displayName = displayName;
	}

	public String getDisplayName() {
		return displayName;
	}
}
