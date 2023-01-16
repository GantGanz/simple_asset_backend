package com.test.simpleasset.dto.company;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class CompanyInsertReqDto {
	@NotBlank(message = "Harus diisi!")
	@Size(max = 50)
	private String companyName;
	@NotBlank(message = "Harus diisi!")
	@Size(max = 5)
	private String companyCode;
	@NotBlank(message = "Harus diisi!")
	private String fileCodes;
	@NotBlank(message = "Harus diisi!")
	@Size(max = 4)
	private String extensions;

	public String getCompanyCode() {
		return companyCode;
	}

	public void setCompanyCode(String companyCode) {
		this.companyCode = companyCode;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
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
