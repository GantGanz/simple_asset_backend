package com.test.simpleasset.dto.checkout;

import java.time.LocalDateTime;

public class CheckOutDataDto {
	private Long checkOutId;
	private Integer version;
	private String trxCode;
	private LocalDateTime checkOutTime;
	private String checkOutLocation;
	private String assetGeneralName;
	private String employeeName;

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

	public Long getCheckOutId() {
		return checkOutId;
	}

	public void setCheckOutId(Long checkOutId) {
		this.checkOutId = checkOutId;
	}

}
