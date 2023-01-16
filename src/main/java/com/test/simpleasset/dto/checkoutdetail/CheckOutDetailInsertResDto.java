package com.test.simpleasset.dto.checkoutdetail;

public class CheckOutDetailInsertResDto {
	private CheckOutDetailInsertDataResDto data;
	private String message;

	public CheckOutDetailInsertDataResDto getData() {
		return data;
	}

	public void setData(CheckOutDetailInsertDataResDto data) {
		this.data = data;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
}
