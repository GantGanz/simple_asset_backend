package com.test.simpleasset.dto.employee;

import lombok.Data;

@Data
public class EmployeeUpdateResDto {
	private EmployeeUpdateReqDto data;
	private String message;
}
