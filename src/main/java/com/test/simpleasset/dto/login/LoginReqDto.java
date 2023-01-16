package com.test.simpleasset.dto.login;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class LoginReqDto {
	@NotBlank(message = "Harus diisi!")
	@Size(max = 30, message = "Terlalu panjang!")
	private String email;
	@NotBlank(message = "Harus diisi!")
	private String password;
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
}
