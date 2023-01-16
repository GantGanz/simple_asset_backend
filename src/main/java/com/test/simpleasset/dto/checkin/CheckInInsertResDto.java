package com.test.simpleasset.dto.checkin;

public class CheckInInsertResDto {
	private CheckInInsertDataResDto data;
	private String message;

	public CheckInInsertDataResDto getData() {
		return data;
	}

	public void setData(CheckInInsertDataResDto data) {
		this.data = data;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
}
