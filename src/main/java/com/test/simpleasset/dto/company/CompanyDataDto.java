package com.test.simpleasset.dto.company;

import lombok.Data;

@Data
public class CompanyDataDto {
	private String companyName;
	private String companyCode;
	private Long fileId;
	private Long companyId;
	private Integer version;
	private Boolean isActive;
}
