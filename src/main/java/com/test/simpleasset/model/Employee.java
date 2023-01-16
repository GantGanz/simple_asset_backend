package com.test.simpleasset.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "employee", uniqueConstraints = @UniqueConstraint(name = "employee_name_code_ck", columnNames = {
		"employee_name", "employee_code" }))
public class Employee extends BaseModel {

	@Column(name = "employee_name", nullable = false, length = 50)
	private String employeeName;

	@Column(name = "employee_code", nullable = false, length = 5, unique = true)
	private String employeeCode;

	public String getEmployeeName() {
		return employeeName;
	}

	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}

	public String getEmployeeCode() {
		return employeeCode;
	}

	public void setEmployeeCode(String employeeCode) {
		this.employeeCode = employeeCode;
	}
}
