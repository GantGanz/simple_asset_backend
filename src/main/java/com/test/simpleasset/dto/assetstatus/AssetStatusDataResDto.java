package com.test.simpleasset.dto.assetstatus;

public class AssetStatusDataResDto {
	private AssetStatusDataDto data;
	private String message;

	public AssetStatusDataDto getData() {
		return data;
	}

	public void setData(AssetStatusDataDto data) {
		this.data = data;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
}
