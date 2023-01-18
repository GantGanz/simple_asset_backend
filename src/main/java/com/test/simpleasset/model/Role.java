package com.test.simpleasset.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Table(name = "user_role", uniqueConstraints = 
	@UniqueConstraint(
		name = "role_name_code_ck",
		columnNames = {"role_name", "role_code"}
	)
)
@Data
@EqualsAndHashCode(callSuper = false)
public class Role extends BaseModel{

	@Column(name = "role_code", nullable = false, length=5, unique=true)
	private String roleCode;
	
	@Column(name = "role_name", nullable = false, length=5)
	private String roleName;
}
