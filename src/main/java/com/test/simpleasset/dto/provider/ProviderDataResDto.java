package com.test.simpleasset.dto.provider;

public class ProviderDataResDto {
	private ProviderDataDto data;
	private String message;

	public ProviderDataDto getData() {
		return data;
	}

	public void setData(ProviderDataDto data) {
		this.data = data;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
}
