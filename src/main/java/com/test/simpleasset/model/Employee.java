package com.test.simpleasset.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Table(name = "employee", uniqueConstraints = @UniqueConstraint(name = "employee_name_code_ck", columnNames = {
		"employee_name", "employee_code" }))
@Data
@EqualsAndHashCode(callSuper = false)
public class Employee extends BaseModel {

	@Column(name = "employee_name", nullable = false, length = 50)
	private String employeeName;

	@Column(name = "employee_code", nullable = false, length = 5, unique = true)
	private String employeeCode;
}
