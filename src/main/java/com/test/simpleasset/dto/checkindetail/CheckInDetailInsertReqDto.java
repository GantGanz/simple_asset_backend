package com.test.simpleasset.dto.checkindetail;

import javax.validation.constraints.NotNull;

public class CheckInDetailInsertReqDto {
	@NotNull(message = "Check In Id di Detail Harus diisi!")
	private Long checkInId;
	@NotNull(message = "Check Out Detail di Detail Harus diisi!")
	private Long checkOutDetailId;
	@NotNull(message = "Asset Status di Detail Harus diisi!")
	private Long assetStatusId;

	public Long getCheckInId() {
		return checkInId;
	}

	public void setCheckInId(Long checkInId) {
		this.checkInId = checkInId;
	}

	public Long getCheckOutDetailId() {
		return checkOutDetailId;
	}

	public void setCheckOutDetailId(Long checkOutDetailId) {
		this.checkOutDetailId = checkOutDetailId;
	}

	public Long getAssetStatusId() {
		return assetStatusId;
	}

	public void setAssetStatusId(Long assetStatusId) {
		this.assetStatusId = assetStatusId;
	}
}
