package com.test.simpleasset.dto.login;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.Data;

@Data
public class LoginReqDto {
	@NotBlank(message = "Harus diisi!")
	@Size(max = 30, message = "Terlalu panjang!")
	private String email;
	@NotBlank(message = "Harus diisi!")
	private String password;
}
