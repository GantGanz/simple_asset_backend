package com.test.simpleasset.dto.checkout;

import java.util.List;

import javax.validation.constraints.NotEmpty;

import com.test.simpleasset.dto.checkoutdetail.CheckOutDetailInsertReqDto;

import lombok.Data;

@Data
public class CheckOutInsertReqDto {
	private String checkOutLocation;
	private Long assetGeneralId;
	private Long employeeId;
	@NotEmpty(message = "Harus diisi!")
	private List<CheckOutDetailInsertReqDto> checkOutDetails;
}
