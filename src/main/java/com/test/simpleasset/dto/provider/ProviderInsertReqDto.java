package com.test.simpleasset.dto.provider;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Data;

@Data
public class ProviderInsertReqDto {
	@NotBlank(message = "Name Harus diisi!")
	@Size(max = 50, message = "Terlalu panjang!")
	private String providerName;
	@NotBlank(message = "Code Harus diisi!")
	@Size(max = 5, message = "Terlalu panjang!")
	private String providerCode;
	@NotNull(message = "Store Harus diisi!")
	private Long storeId;
	@NotBlank(message = "File Codes Harus diisi!")
	private String fileCodes;
	@NotBlank(message = "Harus diisi!")
	@Size(max = 4, message = "Extensions Terlalu panjang!")
	private String extensions;
}
