package com.test.simpleasset.dto.checkindetail;

import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
public class CheckInDetailInsertReqDto {
	@NotNull(message = "Check In Id di Detail Harus diisi!")
	private Long checkInId;
	@NotNull(message = "Check Out Detail di Detail Harus diisi!")
	private Long checkOutDetailId;
	@NotNull(message = "Asset Status di Detail Harus diisi!")
	private Long assetStatusId;
}
