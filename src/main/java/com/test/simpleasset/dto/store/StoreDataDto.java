package com.test.simpleasset.dto.store;

import lombok.Data;

@Data
public class StoreDataDto {
	private Long storeId;
	private Integer version;
	private String storeName;
	private String storeCode;
	private Long fileId;
	private Boolean isActive;
}
