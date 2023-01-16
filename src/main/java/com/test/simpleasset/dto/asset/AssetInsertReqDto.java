package com.test.simpleasset.dto.asset;

import java.time.LocalDate;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class AssetInsertReqDto {

	@NotBlank(message = "Name Harus diisi!")
	@Size(max = 50)
	private String assetName;

	@Size(max = 5)
	private String assetCode;

	@NotBlank(message = "Invoice Harus diisi!")
	@Size(max = 30)
	private String invoiceNumber;

	@NotBlank(message = "Serial Number Harus diisi!")
	@Size(max = 30)
	private String serialNumber;

	private LocalDate expiredDate;

	@NotNull(message = "Company Harus diisi!")
	private Long companyId;

	@NotNull(message = "Provider Harus diisi!")
	private Long providerId;

	@NotNull(message = "Type Harus diisi!")
	private Long assetTypeId;

	@NotNull(message = "Status Harus diisi!")
	private Long assetStatusId;

	@NotBlank(message = "File Codes harus diisi!")
	private String fileCodes;

	@NotBlank(message = "Extensions harus diisi!")
	@Size(max = 4)
	private String extensions;

	public String getAssetCode() {
		return assetCode;
	}

	public void setAssetCode(String assetCode) {
		this.assetCode = assetCode;
	}

	public String getAssetName() {
		return assetName;
	}

	public void setAssetName(String assetName) {
		this.assetName = assetName;
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

	public String getInvoiceNumber() {
		return invoiceNumber;
	}

	public void setInvoiceNumber(String invoiceNumber) {
		this.invoiceNumber = invoiceNumber;
	}

	public String getSerialNumber() {
		return serialNumber;
	}

	public void setSerialNumber(String serialNumber) {
		this.serialNumber = serialNumber;
	}

	public LocalDate getExpiredDate() {
		return expiredDate;
	}

	public void setExpiredDate(LocalDate expiredDate) {
		this.expiredDate = expiredDate;
	}

	public Long getProviderId() {
		return providerId;
	}

	public void setProviderId(Long providerId) {
		this.providerId = providerId;
	}

	public Long getAssetTypeId() {
		return assetTypeId;
	}

	public void setAssetTypeId(Long assetTypeId) {
		this.assetTypeId = assetTypeId;
	}

	public Long getAssetStatusId() {
		return assetStatusId;
	}

	public void setAssetStatusId(Long assetStatusId) {
		this.assetStatusId = assetStatusId;
	}

	public Long getCompanyId() {
		return companyId;
	}

	public void setCompanyId(Long companyId) {
		this.companyId = companyId;
	}

}
