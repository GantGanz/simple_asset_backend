package com.test.simpleasset.dto.employee;

public class EmployeeDataResDto {
	private EmployeeDataDto data;
	private String message;

	public EmployeeDataDto getData() {
		return data;
	}

	public void setData(EmployeeDataDto data) {
		this.data = data;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
}
