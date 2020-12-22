package com.zxe.common.utils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class CommonUtils {

    public static Date getCurrentTime(){
        Date dt=new Date();//如果不需要格式,可直接用dt,dt就是当前系统时间
        DateFormat df = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");//设置显示格式
        String nowTime = df.format(dt);
        return new Date(nowTime);
    }
}
