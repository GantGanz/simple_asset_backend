package com.test.simpleasset.dto.user;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Data;

@Data
public class UserUpdateReqDto {
	@NotNull(message = "Id Harus diisi!")
	private Long userId;
	@NotBlank(message = "Fullname Harus diisi!")
	@Size(max = 50, message = "Terlalu panjang!")
	private String fullname;
	private Integer version;
	@NotBlank(message = "file codes Harus diisi!")
	private String fileCodes;
	@NotBlank(message = "Harus diisi!")
	@Size(max = 4, message = "extensions Terlalu panjang!")
	private String extensions;
	private Boolean isActive;
}
