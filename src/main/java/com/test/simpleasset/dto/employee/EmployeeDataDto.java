package com.test.simpleasset.dto.employee;

import lombok.Data;

@Data
public class EmployeeDataDto {
	private String employeeName;
	private String employeeCode;
	private Long employeeId;
	private Integer version;
	private Boolean isActive;
}
