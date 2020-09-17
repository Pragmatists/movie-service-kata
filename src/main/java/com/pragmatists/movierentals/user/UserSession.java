package com.pragmatists.movierentals.user;

public class UserSession {

	private static final UserSession userSession = new UserSession();
	
	public static UserSession getInstance() {
		return userSession;
	}

	public User getLoggedUser() {
		throw new RuntimeException("UserSession.getLoggedUser() is not implemented");
	}

}
