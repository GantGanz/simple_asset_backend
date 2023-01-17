package com.test.simpleasset.dto.checkin;

import java.util.List;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.test.simpleasset.dto.checkindetail.CheckInDetailInsertReqDto;

import lombok.Data;

@Data
public class CheckInInsertReqDto {
	@NotNull(message = "Check Out Id di Check In Harus diisi!")
	private Long checkOutId;
	@NotEmpty(message = "Check In Details di Check In Harus diisi!")
	private List<CheckInDetailInsertReqDto> checkInDetails;
}
