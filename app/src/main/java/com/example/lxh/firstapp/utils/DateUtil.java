package com.example.lxh.firstapp.utils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Created by lxh on 2018/8/8.
 */

public class DateUtil {

    // 按照传入字符串格式加载
    public static String parseDate(final String str, final String formatStr) {
        try {
            SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
            SimpleDateFormat dateFormat = new SimpleDateFormat(formatStr, Locale.CHINA);
            Date date = sdf1.parse(str);
            return dateFormat.format(date);
        } catch (Exception e) {
        }
        return str;
    }

    public static Date fromDate(final String str) {
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.CHINA);
            Date date = dateFormat.parse(str);
            return date;
        } catch (Exception e) {
        }
        return null;
    }
}
