package com.test.simpleasset.dto.checkout;

import java.util.List;

import javax.validation.constraints.NotEmpty;

import com.test.simpleasset.dto.checkoutdetail.CheckOutDetailInsertReqDto;

public class CheckOutInsertReqDto {
	private String checkOutLocation;
	private Long assetGeneralId;
	private Long employeeId;
	@NotEmpty(message = "Harus diisi!")
	private List<CheckOutDetailInsertReqDto> checkOutDetails;

	public String getCheckOutLocation() {
		return checkOutLocation;
	}

	public void setCheckOutLocation(String checkOutLocation) {
		this.checkOutLocation = checkOutLocation;
	}

	public Long getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(Long employeeId) {
		this.employeeId = employeeId;
	}

	public Long getAssetGeneralId() {
		return assetGeneralId;
	}

	public void setAssetGeneralId(Long assetGeneralId) {
		this.assetGeneralId = assetGeneralId;
	}

	public List<CheckOutDetailInsertReqDto> getCheckOutDetails() {
		return checkOutDetails;
	}

	public void setCheckOutDetails(List<CheckOutDetailInsertReqDto> checkOutDetails) {
		this.checkOutDetails = checkOutDetails;
	}

}
