package com.test.simpleasset.dto.checkindetail;

import java.time.LocalDateTime;

public class CheckInDetailDataDto {
	private Long checkInDetailId;
	private Integer version;
	private String trxCode;
	private String assetName;
	private String assetStatus;
	private LocalDateTime checkInTime;

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

	public String getAssetName() {
		return assetName;
	}

	public void setAssetName(String assetName) {
		this.assetName = assetName;
	}

	public String getAssetStatus() {
		return assetStatus;
	}

	public void setAssetStatus(String assetStatus) {
		this.assetStatus = assetStatus;
	}

	public LocalDateTime getCheckInTime() {
		return checkInTime;
	}

	public void setCheckInTime(LocalDateTime checkInTime) {
		this.checkInTime = checkInTime;
	}

	public Long getCheckInDetailId() {
		return checkInDetailId;
	}

	public void setCheckInDetailId(Long checkInDetailId) {
		this.checkInDetailId = checkInDetailId;
	}

}
