package com.test.simpleasset.dto.user;

public class UserUpdatePasswordResDto {
	private UserUpdatePasswordReqDto data;
	private String message;

	public UserUpdatePasswordReqDto getData() {
		return data;
	}

	public void setData(UserUpdatePasswordReqDto data) {
		this.data = data;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
}
