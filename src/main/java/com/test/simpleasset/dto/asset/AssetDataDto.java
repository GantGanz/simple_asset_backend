package com.test.simpleasset.dto.asset;

import java.time.LocalDate;

import lombok.Data;

@Data
public class AssetDataDto {
	private String assetName;
	private String invoiceNumber;
	private String serialNumber;
	private LocalDate expiredDate;
	private String companyName;
	private String providerName;
	private String assetTypeName;
	private String assetStatusName;
	private Integer version;
	private Long assetId;
	private Long fileId;
	private Boolean isActive;
}
