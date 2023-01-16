package com.test.simpleasset.dto.asset;

public class AssetUpdateResDto {
	private AssetUpdateReqDto data;
	private String message;

	public AssetUpdateReqDto getData() {
		return data;
	}

	public void setData(AssetUpdateReqDto data) {
		this.data = data;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
}
