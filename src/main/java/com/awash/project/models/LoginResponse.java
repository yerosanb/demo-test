package com.awash.project.models;

public class LoginResponse {
	private String message;
    private String username;
    private String token;
    public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public LoginResponse(String message, String username,String token) {
        this.message = message;
        this.username = username;
        this.token=token;
    }

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}
    
    
}
