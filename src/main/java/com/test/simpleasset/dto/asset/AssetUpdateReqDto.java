package com.test.simpleasset.dto.asset;

import java.time.LocalDate;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Data;

@Data
public class AssetUpdateReqDto {
	@NotNull(message = "Harus diisi!")
	private Long assetId;
	@NotBlank(message = "Harus diisi!")
	@Size(max = 50)
	private String assetName;
	@NotBlank(message = "Harus diisi!")
	@Size(max = 30)
	private String invoiceNumber;
	@NotBlank(message = "Harus diisi!")
	@Size(max = 30)
	private String serialNumber;
	@NotNull(message = "Is Active Harus diisi!")
	private Boolean isActive;

	private LocalDate expiredDate;

	@NotNull(message = "Harus diisi!")
	private Long providerId;

	@NotNull(message = "Harus diisi!")
	private Long companyId;

	@NotNull(message = "Harus diisi!")
	private Long assetTypeId;

	@NotNull(message = "Harus diisi!")
	private Long assetStatusId;

	@NotBlank(message = "Harus diisi!")
	private String fileCodes;
	@NotBlank(message = "Harus diisi!")
	@Size(max = 4)
	private String extensions;

	private Integer version;
}
