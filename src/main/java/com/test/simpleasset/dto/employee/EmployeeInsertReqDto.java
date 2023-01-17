package com.test.simpleasset.dto.employee;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.Data;

@Data
public class EmployeeInsertReqDto {
	@NotBlank(message = "Harus diisi!")
	@Size(max = 50, message = "Terlalu panjang!")
	private String employeeName;
	@NotBlank(message = "Harus diisi!")
	@Size(max = 5, message = "Terlalu panjang!")
	private String employeeCode;
}
