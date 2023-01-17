package com.test.simpleasset.dto.company;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Data;

@Data
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
}
