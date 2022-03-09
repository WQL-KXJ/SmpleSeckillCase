package com.my.seckill.ATO;

public enum MyCode{

    SUCCEED(200,"请求成功"),
    EXCEPTION(404,"请求失败"),
    UNSUCCEED(202,"请求失败"),
    UNAUTHENTICATION(207,"认证失败"),
    AUTHENTICATION(200,"认证成功"),
    PASSWORDERROR(209,"密码错误"),
    USERERROR(210,"用户不存在"),
    ADDRESSERROR(403,"地址错误"),
    VERIFICATIONERROR(409,"验证码错误"),
    CURRENTLIMIT(410,"点击过于频繁");


    private int code;
    private String message;

    MyCode(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "{" +
                "code=" + code +
                ", message='" + message + '\'' +
                '}';
    }
}
