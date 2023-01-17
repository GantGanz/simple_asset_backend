package com.test.simpleasset.dto.checkindetail;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class CheckInDetailDataDto {
	private Long checkInDetailId;
	private Integer version;
	private String trxCode;
	private String assetName;
	private String assetStatus;
	private LocalDateTime checkInTime;
}
