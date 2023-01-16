package com.test.simpleasset.dto.employee;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class EmployeeUpdateReqDto {
	@NotNull(message = "Harus diisi!")
	private Long employeeId;
	@NotBlank(message = "Harus diisi!")
	private String employeeName;
	private Integer version;
	@NotNull(message = "Harus diisi!")
	private Boolean isActive;

	public String getEmployeeName() {
		return employeeName;
	}

	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}

	public Long getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(Long employeeId) {
		this.employeeId = employeeId;
	}

	public Integer getVersion() {
		return version;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}

	public Boolean getIsActive() {
		return isActive;
	}

	public void setIsActive(Boolean isActive) {
		this.isActive = isActive;
	}

}
