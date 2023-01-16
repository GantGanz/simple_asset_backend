package com.test.simpleasset.dto.company;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class CompanyUpdateReqDto {
	@NotNull(message = "Id Harus diisi!")
	private Long companyId;
	@NotBlank(message = "Name Harus diisi!")
	@Size(max = 50)
	private String companyName;
	private Integer version;
	@NotBlank(message = "File Codes Harus diisi!")
	private String fileCodes;
	@NotBlank(message = "Extensions Harus diisi!")
	@Size(max = 4)
	private String extensions;
	@NotNull(message = "Is Active Harus diisi!")
	private Boolean isActive;

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public Long getCompanyId() {
		return companyId;
	}

	public void setCompanyId(Long companyId) {
		this.companyId = companyId;
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

	public Boolean getIsActive() {
		return isActive;
	}

	public void setIsActive(Boolean isActive) {
		this.isActive = isActive;
	}

}
