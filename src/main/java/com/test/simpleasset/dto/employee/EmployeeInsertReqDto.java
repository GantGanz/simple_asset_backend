package com.test.simpleasset.dto.employee;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class EmployeeInsertReqDto {
	@NotBlank(message = "Harus diisi!")
	@Size(max = 50, message = "Terlalu panjang!")
	private String employeeName;
	@NotBlank(message = "Harus diisi!")
	@Size(max = 5, message = "Terlalu panjang!")
	private String employeeCode;

	public String getEmployeeCode() {
		return employeeCode;
	}

	public void setEmployeeCode(String employeeCode) {
		this.employeeCode = employeeCode;
	}

	public String getEmployeeName() {
		return employeeName;
	}

	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}
}
