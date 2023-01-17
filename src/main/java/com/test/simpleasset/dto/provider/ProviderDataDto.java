package com.test.simpleasset.dto.provider;

import lombok.Data;

@Data
public class ProviderDataDto {
	private String providerName;
	private String providerCode;
	private String storeName;
	private Long fileId;
	private Long providerId;
	private Integer version;
	private Boolean isActive;
}
