package com.test.simpleasset.dto.role;

import lombok.Data;

@Data
public class RoleDataDto {
	private Long roleId;
	private Integer version;
	private String roleName;
	private String roleCode;
}
