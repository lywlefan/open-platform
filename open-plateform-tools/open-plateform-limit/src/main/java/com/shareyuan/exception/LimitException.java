package com.shareyuan.exception;

import com.shareyuan.common.ErrorCodeEnum;
import lombok.Getter;

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
