package com.test.simpleasset.dto.assettype;

import lombok.Data;

@Data
public class AssetTypeDataDto {
	private Long assetTypeId;
	private Integer version;
	private String assetTypeName;
	private String assetTypeCode;
}
