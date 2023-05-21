package com.sxy.zongce.exception;

public class ExistingException extends RuntimeException{

    private int code = 500;
    private String msg = "服务器异常";

    public ExistingException(ResponseCode responseCode) {
        super();
        this.code = responseCode.getCode();
        this.msg = responseCode.getMsg();
    }

    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}
