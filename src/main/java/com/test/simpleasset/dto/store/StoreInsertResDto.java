package com.test.simpleasset.dto.store;

public class StoreInsertResDto {
	private StoreInsertDataResDto data;
	private String message;

	public StoreInsertDataResDto getData() {
		return data;
	}

	public void setData(StoreInsertDataResDto data) {
		this.data = data;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
}
