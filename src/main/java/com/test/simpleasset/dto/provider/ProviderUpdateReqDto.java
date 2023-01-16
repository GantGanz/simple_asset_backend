package com.test.simpleasset.dto.provider;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class ProviderUpdateReqDto {
	@NotNull(message = "id Harus diisi!")
	private Long providerId;
	@NotBlank(message = "name Harus diisi!")
	@Size(max = 50, message = "Terlalu panjang!")
	private String providerName;
	@NotNull(message = "store Harus diisi!")
	private Long storeId;
	@NotBlank(message = "file Harus diisi!")
	private String fileCodes;
	@NotBlank(message = "extensions Harus diisi!")
	@Size(max = 4, message = "Terlalu panjang!")
	private String extensions;
	private Integer version;
	@NotNull(message = "Is Active Harus diisi!")
	private Boolean isActive;
	
	public Boolean getIsActive() {
		return isActive;
	}

	public void setIsActive(Boolean isActive) {
		this.isActive = isActive;
	}
	
	public String getProviderName() {
		return providerName;
	}

	public void setProviderName(String providerName) {
		this.providerName = providerName;
	}

	public Long getProviderId() {
		return providerId;
	}

	public void setProviderId(Long providerId) {
		this.providerId = providerId;
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

	public Long getStoreId() {
		return storeId;
	}

	public void setStoreId(Long storeId) {
		this.storeId = storeId;
	}

}
