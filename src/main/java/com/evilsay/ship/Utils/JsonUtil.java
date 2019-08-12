package com.evilsay.ship.Utils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * @Author: EvilSay
 * @Date: 2019/1/29 23:34
 */
public class JsonUtil {
    public static String toJson(Object object){
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.setPrettyPrinting();
        Gson gson = gsonBuilder.create();
        return gson.toJson(object);
    }
}
