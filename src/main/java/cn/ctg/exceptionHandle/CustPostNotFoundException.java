package cn.ctg.exceptionHandle;

public class CustPostNotFoundException extends RuntimeException{
    private static final long serialVersionUID=1L;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public int getResultCode() {
        return resultCode;
    }

    public void setResultCode(int resultCode) {
        this.resultCode = resultCode;
    }

    private int resultCode;
    private String msg;

    public CustPostNotFoundException(int retCode, String msg) {
        this.resultCode = retCode;
        this.msg = msg;
    }

}
