package com.test.simpleasset.dto.store;

public class StoreUpdateResDto {
	private StoreUpdateReqDto data;
	private String message;

	public StoreUpdateReqDto getData() {
		return data;
	}

	public void setData(StoreUpdateReqDto data) {
		this.data = data;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
}
