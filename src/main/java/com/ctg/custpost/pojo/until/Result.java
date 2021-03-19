package com.ctg.custpost.pojo.until;

public class Result<T> {
    private int resultCode;
    private String msg;
    private T data;

    public Result(int resultCode,String msg,T data) {
        this.resultCode = resultCode;
        this.msg = msg;
        this.data = data;
    }

    public Result() {

    }

    public int getResultCode() {
        return resultCode;
    }

    public void setResultCode(int resultCode) {
        this.resultCode = resultCode;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
