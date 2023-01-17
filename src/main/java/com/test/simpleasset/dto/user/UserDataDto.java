package com.test.simpleasset.dto.user;

import lombok.Data;

@Data
public class UserDataDto {
	private Long userId;
	private Integer version;
	private String fullname;
	private String email;
	private Long fileId;
	private String roleName;
	private Boolean isActive;
}