package com.test.simpleasset.dto.user;

import lombok.Data;

@Data
public class UserUpdatePasswordResDto {
	private UserUpdatePasswordReqDto data;
	private String message;
}
