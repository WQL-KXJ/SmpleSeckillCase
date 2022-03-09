package com.my.seckill.util;

import java.util.Random;

public class MySlatUtil {

    static char[] slat = {'a','b','c','d','e','f','g','h','i','j','k','l','n','m','o','p','q','r','s','t','u','v','w','x','y','z'};

    //生成随机盐
    public static String getSlat(int size){

        StringBuilder builder = new StringBuilder();

        Random random = new Random();

        for(int i=0;i<size;i++){

            builder.append(slat[random.nextInt(25)]);
        }
        return builder.toString();
    }
}
