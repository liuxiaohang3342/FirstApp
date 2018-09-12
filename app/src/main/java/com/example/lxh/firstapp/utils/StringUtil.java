package com.example.lxh.firstapp.utils;

/**
 * Created by lxh on 2018/9/12.
 */

public class StringUtil {

    public static String arryToString(String[] strings, String splite) {
        StringBuilder builder = new StringBuilder();
        if (strings == null || strings.length == 0) {
            return builder.toString();
        }
        for (String item : strings) {
            builder.append(item).append(splite);
        }
        builder.deleteCharAt(builder.length() - 1);
        return builder.toString();
    }

}
