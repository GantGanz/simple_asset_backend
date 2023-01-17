package com.test.simpleasset.dto.checkin;

import java.time.LocalDateTime;

import lombok.Data;

@Data
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
}
