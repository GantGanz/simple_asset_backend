package com.test.simpleasset.dto.checkin;

import java.util.List;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.test.simpleasset.dto.checkindetail.CheckInDetailInsertReqDto;

public class CheckInInsertReqDto {
	@NotNull(message = "Check Out Id di Check In Harus diisi!")
	private Long checkOutId;
	@NotEmpty(message = "Check In Details di Check In Harus diisi!")
	private List<CheckInDetailInsertReqDto> checkInDetails;

	public Long getCheckOutId() {
		return checkOutId;
	}

	public void setCheckOutId(Long checkOutId) {
		this.checkOutId = checkOutId;
	}

	public List<CheckInDetailInsertReqDto> getCheckInDetails() {
		return checkInDetails;
	}

	public void setCheckInDetails(List<CheckInDetailInsertReqDto> checkInDetails) {
		this.checkInDetails = checkInDetails;
	}

}
