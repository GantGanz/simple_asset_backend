package com.test.simpleasset.dto.provider;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class ProviderInsertReqDto {
	@NotBlank(message = "Name Harus diisi!")
	@Size(max = 50, message = "Terlalu panjang!")
	private String providerName;
	@NotBlank(message = "Code Harus diisi!")
	@Size(max = 5, message = "Terlalu panjang!")
	private String providerCode;
	@NotNull(message = "Store Harus diisi!")
	private Long storeId;
	@NotBlank(message = "File Codes Harus diisi!")
	private String fileCodes;
	@NotBlank(message = "Harus diisi!")
	@Size(max = 4, message = "Extensions Terlalu panjang!")
	private String extensions;

	public String getProviderCode() {
		return providerCode;
	}

	public void setProviderCode(String providerCode) {
		this.providerCode = providerCode;
	}

	public String getProviderName() {
		return providerName;
	}

	public void setProviderName(String providerName) {
		this.providerName = providerName;
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
