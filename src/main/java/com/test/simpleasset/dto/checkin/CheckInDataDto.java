package com.test.simpleasset.dto.checkin;

import java.time.LocalDateTime;

public class CheckInDataDto {
	private Long checkInId;
	private Integer version;
	private String trxCode;
	private LocalDateTime checkOutTime;
	private LocalDateTime createdAt;
	private LocalDateTime updatedAt;
	private String checkOutLocation;
	private String assetGeneralName;
	private String employeeName;

	public Long getCheckInId() {
		return checkInId;
	}

	public void setCheckInId(Long checkInId) {
		this.checkInId = checkInId;
	}

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

	public LocalDateTime getCheckOutTime() {
		return checkOutTime;
	}

	public void setCheckOutTime(LocalDateTime checkOutTime) {
		this.checkOutTime = checkOutTime;
	}

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}

	public LocalDateTime getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(LocalDateTime updatedAt) {
		this.updatedAt = updatedAt;
	}

	public String getCheckOutLocation() {
		return checkOutLocation;
	}

	public void setCheckOutLocation(String checkOutLocation) {
		this.checkOutLocation = checkOutLocation;
	}

	public String getAssetGeneralName() {
		return assetGeneralName;
	}

	public void setAssetGeneralName(String assetGeneralName) {
		this.assetGeneralName = assetGeneralName;
	}

	public String getEmployeeName() {
		return employeeName;
	}

	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}

}
