package com.test.simpleasset.dto.error;

import lombok.Data;

@Data
public class ErrorResDto<T> {
	private T message;
}
