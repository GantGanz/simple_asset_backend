package com.test.simpleasset.dto.assetstatus;

import lombok.Data;

@Data
public class AssetStatusDataDto {
	private Long assetStatusId;
	private Integer version;
	private String assetStatusName;
	private String assetStatusCode;
}
