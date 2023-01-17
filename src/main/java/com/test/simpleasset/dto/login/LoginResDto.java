package com.test.simpleasset.dto.login;

import lombok.Data;

@Data
public class LoginResDto {
	private Long id;
	private String fullname;
	private String token;
	private String roleCode;
	private String email;
	private Long photoId;
}
