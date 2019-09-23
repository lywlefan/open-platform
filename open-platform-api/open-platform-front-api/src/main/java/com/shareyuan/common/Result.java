package com.shareyuan.common;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class Result {

    private static String SUCCESS = "0000";
    private static String SUCCESS_MSG = "成功";

    private String retCode;

    private String retMsg;

    private Object data;

    public Result(){
        this.retCode = SUCCESS;
        this.retMsg = SUCCESS_MSG;
        this.data ="";
    }

    public Result(Object data){
        this.retCode = SUCCESS;
        this.retMsg = SUCCESS_MSG;
        this.data = data;
    }

    public Result(String code, String msg){
        this.retCode = code;
        this.retMsg = msg;
    }

}
