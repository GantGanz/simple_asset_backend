package com.test.simpleasset.dto.provider;

public class ProviderInsertResDto {
	private ProviderInsertDataResDto data;
	private String message;

	public ProviderInsertDataResDto getData() {
		return data;
	}

	public void setData(ProviderInsertDataResDto data) {
		this.data = data;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
}
