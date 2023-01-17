package com.test.simpleasset.dto.checkout;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class CheckOutDataDto {
	private Long checkOutId;
	private Integer version;
	private String trxCode;
	private LocalDateTime checkOutTime;
	private String checkOutLocation;
	private String assetGeneralName;
	private String employeeName;
}
