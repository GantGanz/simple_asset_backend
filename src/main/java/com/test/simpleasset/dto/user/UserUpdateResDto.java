package com.test.simpleasset.dto.user;

import lombok.Data;

@Data
public class UserUpdateResDto {
	private UserUpdateReqDto data;
	private String message;
}
