package com.shareyuan.exception;

import com.shareyuan.common.ErrorCodeEnum;
import lombok.Getter;

/**
 * @Author : kent
 * @Description : 自定义异常
 * @Date : 11:39 2019/9/17
 */
public class LimitException extends RuntimeException {

    @Getter
    private ErrorCodeEnum errorCodeEnum;

    @Getter
    private String code;

    @Getter
    private String msg;

    public LimitException(ErrorCodeEnum errorCodeEnum) {
        super(errorCodeEnum.getName());
        this.errorCodeEnum = errorCodeEnum;
        this.code = errorCodeEnum.getValue();
        this.msg = errorCodeEnum.getName();
    }

    public LimitException(String code,String msg) {
        super(msg);
        this.code = code;
        this.msg = msg;
    }
}
