package com.test.simpleasset.dto.assetstatus;

public class AssetStatusDataDto {
	private Long assetStatusId;
	private Integer version;
	private String assetStatusName;
	private String assetStatusCode;

	public Integer getVersion() {
		return version;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}

	public String getAssetStatusName() {
		return assetStatusName;
	}

	public void setAssetStatusName(String assetStatusName) {
		this.assetStatusName = assetStatusName;
	}

	public String getAssetStatusCode() {
		return assetStatusCode;
	}

	public void setAssetStatusCode(String assetStatusCode) {
		this.assetStatusCode = assetStatusCode;
	}

	public Long getAssetStatusId() {
		return assetStatusId;
	}

	public void setAssetStatusId(Long assetStatusId) {
		this.assetStatusId = assetStatusId;
	}

}
