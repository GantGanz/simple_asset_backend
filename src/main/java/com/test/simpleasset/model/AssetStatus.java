package com.test.simpleasset.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "asset_status", uniqueConstraints = @UniqueConstraint(name = "asset__status_code_ck", columnNames = {
		"asset_status_name", "asset_status_code" }))
public class AssetStatus extends BaseModel {

	@Column(name = "asset_status_name", nullable = false, length = 30)
	private String assetStatusName;

	@Column(name = "asset_status_code", unique = true, nullable = false, length = 5)
	private String assetStatusCode;

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
}
