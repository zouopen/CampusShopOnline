package com.evilsay.ship.Utils;

import com.evilsay.ship.Enums.CodeEnum;

/**
 * @Author: EvilSay
 * @Date: 2019/2/1 23:44
 */
public class EnumUtils {
    /**
     *
     * @param code 需要对比的枚举参数
     * @param enumClass 需要对比该类里面的枚举参数
     * @param <T> 该泛型继承于CodeEnum
     * @return 数组类
     */
    public static <T extends CodeEnum> T getByCode(Integer code, Class<T> enumClass){
        for (T each : enumClass.getEnumConstants()){
            if (code.equals(each.getCode())){
                return each;
            }
        }
        return null;
    }
}
