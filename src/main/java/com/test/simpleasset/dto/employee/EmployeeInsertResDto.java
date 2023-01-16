package com.test.simpleasset.dto.employee;

public class EmployeeInsertResDto {
	private EmployeeInsertDataResDto data;
	private String message;

	public EmployeeInsertDataResDto getData() {
		return data;
	}

	public void setData(EmployeeInsertDataResDto data) {
		this.data = data;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
}
