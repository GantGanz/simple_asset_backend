package com.test.simpleasset.dto.asset;

public class AssetInsertResDto {
	private AssetInsertDataResDto data;
	private String message;

	public AssetInsertDataResDto getData() {
		return data;
	}

	public void setData(AssetInsertDataResDto data) {
		this.data = data;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
}
