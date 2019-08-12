package com.evilsay.ship.Utils;

import java.util.Random;

/**
 * @Author: EvilSay
 * @Date: 2019/1/23 22:15
 */
public class KeyUtil {
    /**
     * 生成唯一的主键
     * 格式：时间+随机数
     * @return
     */
    public static synchronized String genUniqueKey(){
        Random random = new Random();

        Integer a = random.nextInt(900000)+ 100000;

        return System.currentTimeMillis() + String.valueOf(a);
    }
    /**
     * 生成唯一的shopType
     * 格式：时间+随机数
     * @return
     */
    public static synchronized Integer genShopTypeKey(){
        Random random = new Random();

        Integer a = random.nextInt(9) + 1;
        Long l = System.currentTimeMillis()/10000;

        return l.intValue() + a;
    }
}
