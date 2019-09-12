package com.shareyuan.common;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ErrorCodeEnum {

	API_LIMIT_EXCEPTION("api限流异常!", "api_limit_exception");

	private String name;
	private String value;

}
