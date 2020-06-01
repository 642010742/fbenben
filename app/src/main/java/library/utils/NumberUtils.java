package library.utils;

import android.text.TextUtils;

import java.text.NumberFormat;

/**
 * @author Administrator
 * @Create 2020/6/1
 * @Description TODO
 * @zmf
 */
public class NumberUtils {


    /**
     * 两个int值相除并保留两位小数
     *
     * @param a
     * @param b
     * @return
     */
    public static String toPercent(int a, int b) {
        if (b == 0) {
            return "0%";
        }
        double num = (double) a / b;
        NumberFormat percentInstance = NumberFormat.getPercentInstance();
        if (b <= 0) {
            return "0%";
        } else {
            return percentInstance.format(num);
        }
    }

    public static String toPercent(double a, double b) {
        if (b == 0) {
            return "0%";
        }
        double num = (double) a / b;
        NumberFormat percentInstance = NumberFormat.getPercentInstance();
        if (b <= 0) {
            return "0%";
        } else {
            return percentInstance.format(num);
        }
    }

    /**
     * 两个int值相除并保留两位小数
     *
     * @param a
     * @param b
     * @return
     */
    public static int toPercentNoSymBol(int a, int b) {
        if (b == 0) {
            return 0;
        }
        double num = (double) a / b;
        NumberFormat percentInstance = NumberFormat.getPercentInstance();
        if (b <= 0) {
            return 0;
        } else {
            return getPercent(percentInstance.format(num));
        }
    }

    public static int getPercent(String value) {
        if (!TextUtils.isEmpty(value)) {
            return Integer.valueOf(value.substring(0, value.length() - 1));
        } else {
            return 0;
        }
    }


    /**
     * 两个int值相除并保留两位小数
     *
     * @param a
     * @param b
     * @return
     */
    public static int percent(int a, int b) {
        if (b == 0) {
            return 0;
        }
        double num = (double) (a * 1.0) / b;
        NumberFormat percentInstance = NumberFormat.getPercentInstance();
        if (num <= 0) {
            return 0;
        } else {
            String format = percentInstance.format(num);
            String substring;
            String[] split = format.split("%");
            substring = split[0];
            return Integer.valueOf(substring);
        }
    }
}
