package com.test.simpleasset.dto.employee;

public class EmployeeUpdateResDto {
	private EmployeeUpdateReqDto data;
	private String message;

	public EmployeeUpdateReqDto getData() {
		return data;
	}

	public void setData(EmployeeUpdateReqDto data) {
		this.data = data;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
}
