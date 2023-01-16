package com.test.simpleasset.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "user_role", uniqueConstraints = 
	@UniqueConstraint(
		name = "role_name_code_ck",
		columnNames = {"role_name", "role_code"}
	)
)
public class Role extends BaseModel{

	@Column(name = "role_code", nullable = false, length=5, unique=true)
	private String roleCode;
	
	@Column(name = "role_name", nullable = false, length=5)
	private String roleName;

	public String getRoleCode() {
		return roleCode;
	}

	public void setRoleCode(String roleCode) {
		this.roleCode = roleCode;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

}
