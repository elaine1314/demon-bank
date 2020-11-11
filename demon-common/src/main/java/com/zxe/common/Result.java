package com.zxe.common;

import lombok.Data;

/**
 * @Author:Elaine
 * @Description:
 * @Date: Created in 8:15 PM 2020/11/3
 * @Version: 1.0
 */
@Data
public class Result {
    private int code;// 20000是正常，非20000是异常
    private Object data;
    private String msg;


    public static Result result(int code,String msg,Object data){
        Result r = new Result();
        r.setCode(code);
        r.setMsg(msg);
        r.setData(data);

        return  r;
    }

    public static Result succ(Object data){
        return result(20000,"操作成功",data);
    }

    public static Result fail(String msg,Object data){
        return result(400,msg,data);
    }

    public static Result fail(String msg){
        return result(400,msg,null);
    }



}
