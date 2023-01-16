package com.test.simpleasset.dto.store;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class StoreUpdateReqDto {
	@NotNull(message = "Harus diisi!")
	private Long storeId;
	@NotBlank(message = "Harus diisi!")
	@Size(max = 50, message = "Terlalu panjang!")
	private String storeName;
	private Integer version;
	@NotBlank(message = "Harus diisi!")
	private String fileCodes;
	@NotBlank(message = "Harus diisi!")
	@Size(max = 4, message = "Terlalu panjang!")
	private String extensions;
	@NotNull(message = "Is Active Harus diisi!")
	private Boolean isActive;
	public Boolean getIsActive() {
		return isActive;
	}

	public void setIsActive(Boolean isActive) {
		this.isActive = isActive;
	}
	
	public String getstoreName() {
		return storeName;
	}

	public void setstoreName(String storeName) {
		this.storeName = storeName;
	}

	public Long getstoreId() {
		return storeId;
	}

	public void setstoreId(Long storeId) {
		this.storeId = storeId;
	}

	public Integer getVersion() {
		return version;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}

	public String getFileCodes() {
		return fileCodes;
	}

	public void setFileCodes(String fileCodes) {
		this.fileCodes = fileCodes;
	}

	public String getExtensions() {
		return extensions;
	}

	public void setExtensions(String extensions) {
		this.extensions = extensions;
	}

}
