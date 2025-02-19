package com.connect2play.entities;

public enum PaymentStatus {
	SUCCESS("Successful"), FAILED("Failed"), PENDING("Pending"), REFUNDED("Refunded");

	private final String displayName;

	PaymentStatus(String displayName) {
		this.displayName = displayName;
	}

	public String getDisplayName() {
		return displayName;
	}
}
