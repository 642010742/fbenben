package library.utils;

import android.text.TextUtils;

import java.text.NumberFormat;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

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
     * 手机号正则校验
     *
     * @param str
     * @return
     * @throws PatternSyntaxException
     */
    public static boolean isChinaPhoneLegal(String str) throws PatternSyntaxException {
        String regExp = "^((13[0-9])|(15[^4])|(166)|(17[0-8])|(18[0-9])|(19[8-9])|(147,145))\\d{8}$";
        Pattern p = Pattern.compile(regExp);
        Matcher m = p.matcher(str);
        return m.matches();
    }

    /**
     * 从一串字符中提取手机号
     *
     * @param text
     * @return
     */
    public static String getPhoneNumbers(String text) {
        String regex = "1[345789]\\d{9}";
        Pattern p = Pattern.compile(regex);
        Matcher m = p.matcher(text);
        while (m.find()) {//看是否能在字符串中找到符合正则表达式的字符串，找到返回true，同时指针指向下一个子序列
            String phone = m.group();            //必须先找再获取
            return phone;
        }
        return "";
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
     * 是否是连续数字
     *
     * @param pwd
     */
    public static boolean isContinuPwd(String pwd) {

        int lastValue;
        int differ = Integer.valueOf(pwd.charAt(1)) - Integer.valueOf(pwd.charAt(0));

        for (int i = 0; i < pwd.length() - 1; i++) {

            lastValue = Integer.valueOf(pwd.charAt(i));
            Integer integer = Integer.valueOf(pwd.charAt(i + 1));

            if ((lastValue + differ != integer)) {
                return false;
            }
        }

        return true;
    }


    /**
     * 是否是重复数字
     *
     * @param pwd
     */
    public static boolean isRepeatPwd(String pwd) {

        int lastValue;
        for (int i = 0; i < pwd.length() - 1; i++) {
            lastValue = Integer.valueOf(pwd.charAt(i));
            Integer integer = Integer.valueOf(pwd.charAt(i + 1));
            if (lastValue != integer) {
                return false;
            }
        }
        return true;
    }

    /**
     * 新建记账本金额校验
     *值为0-999999范围内
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
     * 两个int值相除并保留两位小数 有%
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
     * 两个int值相除并保留两位小数 无%
     * @param a
     * @param b
     * @return
     */
    public static int toPercentNoPercent(int a, int b) {
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

    /**
     * 两个double值相除并保留两位小数
     * @param a
     * @param b
     * @return
     */
    public static String toPercent(double a, double b) {
        if (b == 0) {
            return "0%";
        }
        double num =  a / b;
        NumberFormat percentInstance = NumberFormat.getPercentInstance();
        if (b <= 0) {
            return "0%";
        } else {
            return percentInstance.format(num);
        }
    }

    /**
     * 两个double值相除并保留两位小数 无%
     * @param a
     * @param b
     * @return
     */
    public static int toPercentNoPercent(double a, double b) {
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

}
