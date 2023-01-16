package com.test.simpleasset.dto.checkoutdetail;

import java.time.LocalDate;

public class CheckOutDetailDataDto {
	private Long checkOutDetailId;
	private Integer version;
	private String trxCode;
	private String assetName;
	private LocalDate returnDate;

	public Integer getVersion() {
		return version;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}

	public String getTrxCode() {
		return trxCode;
	}

	public void setTrxCode(String trxCode) {
		this.trxCode = trxCode;
	}

	public Long getCheckOutDetailId() {
		return checkOutDetailId;
	}

	public void setCheckOutDetailId(Long checkOutDetailId) {
		this.checkOutDetailId = checkOutDetailId;
	}

	public String getAssetName() {
		return assetName;
	}

	public void setAssetName(String assetName) {
		this.assetName = assetName;
	}

	public LocalDate getReturnDate() {
		return returnDate;
	}

	public void setReturnDate(LocalDate returnDate) {
		this.returnDate = returnDate;
	}

}
