package com.test.simpleasset.dto.store;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.Data;

@Data
public class StoreInsertReqDto {
	@NotBlank(message = "Harus diisi!")
	@Size(max = 50, message = "Terlalu panjang!")
	private String storeName;
	@NotBlank(message = "Harus diisi!")
	@Size(max = 5, message = "Terlalu panjang!")
	private String storeCode;
	@NotBlank(message = "Harus diisi!")
	private String fileCodes;
	@NotBlank(message = "Harus diisi!")
	@Size(max = 4, message = "Terlalu panjang!")
	private String extensions;
}
