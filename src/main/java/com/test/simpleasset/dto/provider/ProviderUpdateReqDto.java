package com.test.simpleasset.dto.provider;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Data;

@Data
public class ProviderUpdateReqDto {
	@NotNull(message = "id Harus diisi!")
	private Long providerId;
	@NotBlank(message = "name Harus diisi!")
	@Size(max = 50, message = "Terlalu panjang!")
	private String providerName;
	@NotNull(message = "store Harus diisi!")
	private Long storeId;
	@NotBlank(message = "file Harus diisi!")
	private String fileCodes;
	@NotBlank(message = "extensions Harus diisi!")
	@Size(max = 4, message = "Terlalu panjang!")
	private String extensions;
	private Integer version;
	@NotNull(message = "Is Active Harus diisi!")
	private Boolean isActive;
}
