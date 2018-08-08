package com.example.lxh.firstapp.utils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Created by lxh on 2018/8/7.
 */

public class NumberUtil {


    /**
     * 数字显示规则
     * • 小于1万的直接显示数字，大于等于1万的显示为x.x万，最多显示小数点后一位，大于1亿的显示为x.x亿
     */
    public static String formatCount(long count) {
        if (count < 0) {
            // WTF
            return "0";
        }
        StringBuilder builder = new StringBuilder();

        if (count < 10000) {
            builder.append(count);
        } else if (count < 100000000) {
            builder.append(formatWapper(count, 4)).append("万");
        } else {
            builder.append(formatWapper(count, 8)).append("亿");
        }
        return builder.toString();
    }

    // String.format("%.1f", 1.0f * count / 10000) 性能不咋地 ，替换成下面的方法，index是除数比如4是10的4次方（10000）
    public static String formatWapper(long count, int index) {
        // 除数
        double divider = Math.pow(10, index);
        // 小数点前面的值
        int x = (int) (count / divider);
        // 小数点后面取一位
        int y = (int) ((count - x * divider) / Math.pow(10, index - 1));
        return x + "." + y;
    }


}
