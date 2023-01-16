package com.test.simpleasset.dto.checkoutdetail;

import java.time.LocalDate;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class CheckOutDetailInsertReqDto {
	@NotBlank(message = "Harus diisi!")
	private String trxCode;
	@NotNull(message = "Harus diisi!")
	private Long assetId;
	private LocalDate returnDate;

	public String getTrxCode() {
		return trxCode;
	}

	public void setTrxCode(String trxCode) {
		this.trxCode = trxCode;
	}

	public LocalDate getReturnDate() {
		return returnDate;
	}

	public void setReturnDate(LocalDate returnDate) {
		this.returnDate = returnDate;
	}

	public Long getAssetId() {
		return assetId;
	}

	public void setAssetId(Long assetId) {
		this.assetId = assetId;
	}

}
