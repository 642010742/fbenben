package library.utils;

import android.text.TextUtils;

import java.text.NumberFormat;

public class CheckUtils {

    /**
     * 手机号校验
     *
     * @param phone
     * @return
     */
    public static boolean isPhone(String phone) {
        if (phone.length() != 11) {
            return false;
        }
        return true;
    }

    /**
     * 需由6-12位数字、字母或符号组成
     *
     * @param pwd
     * @return
     */
    public static boolean isPwd(String pwd) {
        if (pwd.length() < 6 || pwd.length() > 12) {
            return false;
        }
        return true;
    }

    /**
     * 验证码校验
     *
     * @param code
     * @return
     */
    public static boolean isCode(String code) {

        if (TextUtils.isEmpty(code.trim())) {
            return false;
        }
        return true;
    }

    /**
     * 新建记账本金额校验
     *
     * @param price
     * @return
     */
    public static boolean checkBookPrice(String price) {
        if (TextUtils.isEmpty(price.trim())) {
            return false;
        } else {
            try {
                Integer integer = Integer.valueOf(price);
                if (integer <= 0) {
                    return false;
                } else if (integer > 999999) {
                    return false;
                } else {
                    return true;
                }
            } catch (Exception e) {
                return false;
            }
        }

    }

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


    /**
     * 两个int值相除并保留两位小数
     *
     * @param a
     * @param b
     * @return
     */
    public static String toPercent100(int a, int b) {
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
