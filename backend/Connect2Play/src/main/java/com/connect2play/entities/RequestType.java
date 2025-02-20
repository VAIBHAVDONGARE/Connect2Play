package com.connect2play.entities;

public enum RequestType {
	JOIN_REQUEST("Join Request"), TEAM_INVITE("Team Invite");
	
	private final String displayName;
	
	private RequestType(String displayName)
	{
		this.displayName = displayName;
	}
	
	public String getDisplayName() {
		return displayName;
	}
	
}
