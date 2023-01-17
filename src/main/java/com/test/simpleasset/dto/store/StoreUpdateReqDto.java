package com.test.simpleasset.dto.store;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Data;

@Data
public class StoreUpdateReqDto {
	@NotNull(message = "Harus diisi!")
	private Long storeId;
	@NotBlank(message = "Harus diisi!")
	@Size(max = 50, message = "Terlalu panjang!")
	private String storeName;
	private Integer version;
	@NotBlank(message = "Harus diisi!")
	private String fileCodes;
	@NotBlank(message = "Harus diisi!")
	@Size(max = 4, message = "Terlalu panjang!")
	private String extensions;
	@NotNull(message = "Is Active Harus diisi!")
	private Boolean isActive;
}
