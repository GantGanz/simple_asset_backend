package com.test.simpleasset.dto.provider;

public class ProviderUpdateResDto {
	private ProviderUpdateReqDto data;
	private String message;

	public ProviderUpdateReqDto getData() {
		return data;
	}

	public void setData(ProviderUpdateReqDto data) {
		this.data = data;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
}
