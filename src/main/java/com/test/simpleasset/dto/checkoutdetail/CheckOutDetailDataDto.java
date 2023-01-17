package com.test.simpleasset.dto.checkoutdetail;

import java.time.LocalDate;

import lombok.Data;

@Data
public class CheckOutDetailDataDto {
	private Long checkOutDetailId;
	private Integer version;
	private String trxCode;
	private String assetName;
	private LocalDate returnDate;
}
