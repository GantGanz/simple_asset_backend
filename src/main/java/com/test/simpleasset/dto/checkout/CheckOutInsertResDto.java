package com.test.simpleasset.dto.checkout;

public class CheckOutInsertResDto {
	private CheckOutInsertDataResDto data;
	private String message;

	public CheckOutInsertDataResDto getData() {
		return data;
	}

	public void setData(CheckOutInsertDataResDto data) {
		this.data = data;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
}
