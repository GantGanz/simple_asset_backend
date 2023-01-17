package com.test.simpleasset.dto.company;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.Data;

@Data
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
}
