package com.test.simpleasset.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "asset_type", uniqueConstraints = @UniqueConstraint(name = "asset_type_name_code_ck", columnNames = {
		"asset_type_name", "asset_type_code" }))
public class AssetType extends BaseModel {
	@Column(name = "asset_type_name", nullable = false, length = 30)
	private String assetTypeName;

	@Column(name = "asset_type_code", unique = true, nullable = false, length = 5)
	private String assetTypeCode;

	public String getAssetTypeName() {
		return assetTypeName;
	}

	public void setAssetTypeName(String assetTypeName) {
		this.assetTypeName = assetTypeName;
	}

	public String getAssetTypeCode() {
		return assetTypeCode;
	}

	public void setAssetTypeCode(String assetTypeCode) {
		this.assetTypeCode = assetTypeCode;
	}
}
