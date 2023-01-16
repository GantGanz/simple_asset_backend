package com.test.simpleasset.dto.checkindetail;

public class CheckInDetailInsertResDto {
	private CheckInDetailInsertDataResDto data;
	private String message;

	public CheckInDetailInsertDataResDto getData() {
		return data;
	}

	public void setData(CheckInDetailInsertDataResDto data) {
		this.data = data;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
}
