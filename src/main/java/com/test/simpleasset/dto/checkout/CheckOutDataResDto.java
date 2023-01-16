package com.test.simpleasset.dto.checkout;

public class CheckOutDataResDto {
	private CheckOutDataDto data;
	private String message;

	public CheckOutDataDto getData() {
		return data;
	}

	public void setData(CheckOutDataDto data) {
		this.data = data;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
}
