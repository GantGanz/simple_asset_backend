package com.test.simpleasset.dto.employee;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
public class EmployeeUpdateReqDto {
	@NotNull(message = "Harus diisi!")
	private Long employeeId;
	@NotBlank(message = "Harus diisi!")
	private String employeeName;
	private Integer version;
	@NotNull(message = "Harus diisi!")
	private Boolean isActive;
}
