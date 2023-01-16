package com.test.simpleasset.constant;

public enum UserType {
	ADMIN("super admin", "SA"), 
	NON_ADMIN("non-admin", "NA"); 

	private String roleName;
	private String roleCode;
	
	private UserType(String roleName, String roleCode){
		this.roleName = roleName;
		this.roleCode = roleCode;
	}

	public String getRoleName() {
		return roleName;
	}
	
	public String getRoleCode() {
		return roleCode;
	}

}
