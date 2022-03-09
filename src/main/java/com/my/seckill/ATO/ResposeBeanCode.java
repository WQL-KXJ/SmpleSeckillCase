package com.my.seckill.ATO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

@AllArgsConstructor
@Data
@NoArgsConstructor
//把返回的枚举进行封装
public class ResposeBeanCode {
    int Code;
    String message;
    Object obj;

    public static ResposeBeanCode success(){
        return new ResposeBeanCode(MyCode.AUTHENTICATION.getCode(),MyCode.AUTHENTICATION.getMessage(),null);
    }

    public static ResposeBeanCode error(){

        return new ResposeBeanCode(MyCode.EXCEPTION.getCode(),MyCode.EXCEPTION.getMessage(),null);

    }

    //接口地址错误(接口隐藏)
    public static ResposeBeanCode addresserroe(Map map){

        return new ResposeBeanCode(MyCode.ADDRESSERROR.getCode(),MyCode.ADDRESSERROR.getMessage(),map);
    }
    //接口地址正确(接口隐藏)
    public static ResposeBeanCode addresssuccess(Map map){

        return new ResposeBeanCode(MyCode.SUCCEED.getCode(),MyCode.SUCCEED.getMessage(),map);
    }

    //验证码错误
    public static  ResposeBeanCode verificationerror(){

        return new ResposeBeanCode(MyCode.VERIFICATIONERROR.getCode(),MyCode.VERIFICATIONERROR.getMessage(),null);
    }

    //接口限流
    public  static  ResposeBeanCode currentlimit(){
        return new ResposeBeanCode(MyCode.CURRENTLIMIT.getCode(),MyCode.CURRENTLIMIT.getMessage(),null);
    }

}
