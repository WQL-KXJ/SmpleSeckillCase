package com.my.seckill.util;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Date;
import java.util.Timer;

public class MyTimeUtil {

    public static LocalDateTime getTime(){
        //获取当然日期
        LocalDate date = LocalDate.now();
        //获取当前时间
        LocalTime time = LocalTime.now();
        //对日期和时间进行整合
        LocalDateTime localDateTime = date.atTime(time);

        return localDateTime;
    }

}
