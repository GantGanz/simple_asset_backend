package com.test.simpleasset.dto.user;

import javax.validation.constraints.NotBlank;

import lombok.Data;

@Data
public class UserUpdatePasswordReqDto {
	private Long userId;
	private Integer version;
	@NotBlank(message = "new password Harus diisi!")
	private String newPassword;
	@NotBlank(message = "old password Harus diisi!")
	private String oldPassword;
}
