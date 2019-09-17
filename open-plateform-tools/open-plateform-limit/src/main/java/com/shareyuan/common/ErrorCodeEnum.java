package com.shareyuan.common;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @Author : kent
 * @Description : 错误枚举
 * @Date : 11:32 2019/9/17
 */
@Getter
@AllArgsConstructor
public enum ErrorCodeEnum {

	API_LIMIT_EXCEPTION("api限流异常!", "api_limit_exception");

	private String name;
	private String value;

}
