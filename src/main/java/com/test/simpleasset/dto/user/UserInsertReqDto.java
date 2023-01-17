package com.test.simpleasset.dto.user;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.Data;

@Data
public class UserInsertReqDto {
	@NotBlank(message = "Harus diisi!")
	@Size(max = 50, message = "Terlalu panjang!")
	private String fullname;
	@NotBlank(message = "Harus diisi!")
	@Email(message = "Harus format email!")
	@Size(max = 30, message = "Terlalu panjang!")
	private String email;
	@NotBlank(message = "Harus diisi!")
	private String fileCodes;
	@NotBlank(message = "Harus diisi!")
	@Size(max = 4, message = "Terlalu panjang!")
	private String extensions;
}