package com.test.simpleasset.dto.user;

public class UserUpdateResDto {
	private UserUpdateReqDto data;
	private String message;

	public UserUpdateReqDto getData() {
		return data;
	}

	public void setData(UserUpdateReqDto data) {
		this.data = data;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
}
