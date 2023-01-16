package com.test.simpleasset.dto.company;

public class CompanyUpdateResDto {
	private CompanyUpdateReqDto data;
	private String message;

	public CompanyUpdateReqDto getData() {
		return data;
	}

	public void setData(CompanyUpdateReqDto data) {
		this.data = data;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
}
