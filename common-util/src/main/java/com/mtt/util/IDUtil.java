package com.mtt.util;

import java.util.Random;

public class IDUtil {
    public static String genImageID(){
        //取当前时间的长整形值包含毫秒
        long millis = System.currentTimeMillis();
        //long millis = System.nanoTime();
        //加上三位随机数
        Random random = new Random();
        int end3 = random.nextInt(999);
        //如果不足三位前面补0
        return millis + String.format("%03d", end3);
    }

    public static Long genItemID(){
        //取当前时间的长整形值包含毫秒
        long millis = System.currentTimeMillis();
        //long millis = System.nanoTime();
        //加上三位随机数
        Random random = new Random();
        int end2 = random.nextInt(99);
        //如果不足三位前面补0
        return new Long(millis + String.format("%02d", end2));
    }
}
