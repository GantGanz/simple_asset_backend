package com.test.simpleasset.dto.checkoutdetail;

import java.time.LocalDate;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
public class CheckOutDetailInsertReqDto {
	@NotBlank(message = "Harus diisi!")
	private String trxCode;
	@NotNull(message = "Harus diisi!")
	private Long assetId;
	private LocalDate returnDate;
}
