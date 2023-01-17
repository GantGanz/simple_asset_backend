package com.test.simpleasset.dto.asset;

import java.time.LocalDate;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Data;

@Data
public class AssetInsertReqDto {

	@NotBlank(message = "Name Harus diisi!")
	@Size(max = 50)
	private String assetName;

	@Size(max = 5)
	private String assetCode;

	@NotBlank(message = "Invoice Harus diisi!")
	@Size(max = 30)
	private String invoiceNumber;

	@NotBlank(message = "Serial Number Harus diisi!")
	@Size(max = 30)
	private String serialNumber;

	private LocalDate expiredDate;

	@NotNull(message = "Company Harus diisi!")
	private Long companyId;

	@NotNull(message = "Provider Harus diisi!")
	private Long providerId;

	@NotNull(message = "Type Harus diisi!")
	private Long assetTypeId;

	@NotNull(message = "Status Harus diisi!")
	private Long assetStatusId;

	@NotBlank(message = "File Codes harus diisi!")
	private String fileCodes;

	@NotBlank(message = "Extensions harus diisi!")
	@Size(max = 4)
	private String extensions;
}
