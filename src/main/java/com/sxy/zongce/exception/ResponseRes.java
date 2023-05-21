package com.sxy.zongce.exception;


public class ResponseRes<T> {

    private int code = 200;
    private String msg = "成功";
    private T data;

    public ResponseRes(int code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    //成功且有定义的情况
    public static <T> ResponseRes<T> success(ResponseCode responseCode,T data) {
        ResponseRes<T> res = new ResponseRes<T>(responseCode.getCode(), responseCode.getMsg(), data);
        return res;
    }

    //成功但未定义的情况
    public static <T> ResponseRes<T> success(String msg,T data) {
        ResponseRes<T> res = new ResponseRes<T>(200,msg, data);
        return res;
    }

    //失败且有定义的情况
    public static <T> ResponseRes<T> error(ResponseCode responseCode) {
        ResponseRes<T> res = new ResponseRes<T>(responseCode.getCode(), responseCode.getMsg(), null);
        return res;
    }

    public static <T> ResponseRes<T> error(int code,String msg) {
        ResponseRes<T> res = new ResponseRes<T>(code, msg, null);
        return res;
    }

    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

    public Object getData() {
        return data;
    }
}
