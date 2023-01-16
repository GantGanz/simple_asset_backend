package com.test.simpleasset.dto.checkin;

public class CheckInDataResDto {
	private CheckInDataDto data;
	private String message;

	public CheckInDataDto getData() {
		return data;
	}

	public void setData(CheckInDataDto data) {
		this.data = data;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
}
