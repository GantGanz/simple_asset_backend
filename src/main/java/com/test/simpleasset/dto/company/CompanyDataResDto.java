package com.test.simpleasset.dto.company;

public class CompanyDataResDto {
	private CompanyDataDto data;
	private String message;

	public CompanyDataDto getData() {
		return data;
	}

	public void setData(CompanyDataDto data) {
		this.data = data;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
}
