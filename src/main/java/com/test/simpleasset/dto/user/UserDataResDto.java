package com.test.simpleasset.dto.user;

public class UserDataResDto {
	private UserDataDto data;
	private String message;

	public UserDataDto getData() {
		return data;
	}

	public void setData(UserDataDto data) {
		this.data = data;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
}
