package com.dwz.library.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * 时间字符串与时间戳 互转
 */

public class DateParseUtils {

    private static String pattern = "yyyy-MM-dd HH:mm:ss";

    public static String getDateToString(long time) {
        Date d = new Date(time);
        SimpleDateFormat sf = new SimpleDateFormat("yyyy/MM/dd HH:mm");
        return sf.format(d);
    }

    public static String getDateToString1(long time) {
        Date d = new Date(time);
        SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        return sf.format(d);
    }

    public static String getDateToString2(long time) {
        Date d = new Date(time);
        SimpleDateFormat sf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        return sf.format(d);
    }

    public static String getDateToString3(long time) {
        Date d = new Date(time);
        SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return sf.format(d);
    }

    public static String getDateToString4(long time) {
        Date d = new Date(time);
        SimpleDateFormat sf = new SimpleDateFormat("MM-dd HH:mm");
        return sf.format(d);
    }

    public static String getDateToString5(long time) {
        Date d = new Date(time);
        SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
        return sf.format(d);
    }

    public static String getDateToString6(long time) {
        Date d = new Date(time);
        SimpleDateFormat sf = new SimpleDateFormat("yyyy/MM/dd");
        return sf.format(d);
    }

    public static String getDateToString7(long time) {
        Date d = new Date(time);
        SimpleDateFormat sf = new SimpleDateFormat("yyyy年MM月dd日");
        return sf.format(d);
    }

    public static String getDateToString8(long time) {
        Date d = new Date(time);
        SimpleDateFormat sf = new SimpleDateFormat("yyyy年MM月");
        return sf.format(d);
    }

    public static String getDateToString9(long time) {
        DateFormat formatter = new SimpleDateFormat("mm:ss");
        return formatter.format(new Date(time));
    }

    public static String getDateToString10(long time) {
        DateFormat formatter = new SimpleDateFormat("MM-dd HH:mm:ss");
        return formatter.format(new Date(time));
    }

    public static String getDateToString11(long time) {
        DateFormat formatter = new SimpleDateFormat("HH:mm");
        return formatter.format(new Date(time));
    }

    public static String getDateToString12(long time) {
        DateFormat formatter = new SimpleDateFormat("MM月dd日");
        return formatter.format(new Date(time));
    }

    public static String getDateToString13(long time) {
        DateFormat formatter = new SimpleDateFormat("MM/dd");
        return formatter.format(new Date(time));
    }

    public static String getDateToString14(long time) {
        DateFormat formatter = new SimpleDateFormat("yyyy-MM");
        return formatter.format(new Date(time));
    }

    public static String getDateToString15(long time) {
        Date d = new Date(time);
        SimpleDateFormat sf = new SimpleDateFormat("yyyy.MM.dd");
        return sf.format(d);
    }

    public static String getDayToString16(long time) {
        Date d = new Date(time);
        SimpleDateFormat sf = new SimpleDateFormat("dd");
        return sf.format(d);
    }

    public static String getDateToString17(Date date) {
        SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return sf.format(date);
    }

    public static String getDateToString18(Date date) {
        SimpleDateFormat sf = new SimpleDateFormat("MM-dd");
        return sf.format(date);
    }

    /**
     * 时间语义化
     * 将s转化为00:00,这里的s不是时间戳
     * 使用场景:拿到语音时长为230s:将他转化为00:00格式
     * @param duration
     * @return
     */
    public static String getSToSemantic(int duration) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("mm:ss");
        Date date = new Date(duration);
        String format = simpleDateFormat.format(date);
        return format;
    }

    /**
     * 获取时间
     * @param date
     * @param pattern
     * @return
     */
    public static String getDateToString(Date date, String pattern) {
        SimpleDateFormat sf = new SimpleDateFormat(pattern);
        return sf.format(date);
    }

    /**
     * 获取英文月份
     * @param time
     * @return
     */
    public static String getEnglishMonth(long time) {
        Date d = new Date(time);
        SimpleDateFormat sf = new SimpleDateFormat("MM");
        String month = sf.format(d);
        String monthText = "";
        switch (month) {
            case "01":
                monthText = "Jan";
                break;
            case "02":
                monthText = "Feb";
                break;
            case "03":
                monthText = "Mar";
                break;
            case "04":
                monthText = "Apr";
                break;
            case "05":
                monthText = "May";
                break;
            case "06":
                monthText = "June";
                break;
            case "07":
                monthText = "July";
                break;
            case "08":
                monthText = "Aug";
                break;
            case "09":
                monthText = "Sept";
                break;
            case "10":
                monthText = "Oct";
                break;
            case "11":
                monthText = "Nov";
                break;
            case "12":
                monthText = "Dec";
                break;
        }
        return monthText;
    }

    /**
     * 获取制定时间的时间戳
     *
     * @param time 2017年4月1日
     * @return
     */
    public static long getTimestamp(String time) {
        DateFormat df = new SimpleDateFormat("yyyy年MM月dd日");
        Date date = null;
        try {
            date = df.parse(time);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        return cal.getTimeInMillis();
    }

    public static String getTime(long time) {
//        DateFormat formatter = new SimpleDateFormat("HH:mm:ss");
//        return formatter.format(new Date(time));
        int timeStr = (int) (time % (24 * 60 * 60 * 1000));
        int hour = timeStr / (60 * 60 * 1000);
        int min = (timeStr % (60 * 60 * 1000)) / (60 * 1000);
        int second = (timeStr % (60 * 60 * 1000)) % (60 * 1000) / 1000;
        String hourStr;
        String minStr;
        String secondStr;
        if (hour < 10) {
            hourStr = "0" + hour;
        } else {
            hourStr = hour + "";
        }
        if (min < 10) {
            minStr = "0" + min;
        } else {
            minStr = min + "";
        }
        if (second < 10) {
            secondStr = "0" + second;
        } else {
            secondStr = second + "";
        }
        return hourStr + ":" + minStr + ":" + secondStr;
    }

    public static long getCurrentTimestamp() {
        return System.currentTimeMillis();
    }

    /**
     * 获取某年某月有多少天
     *
     * @param year  年
     * @param month 月
     * @return 某年某月有多少天
     */
    public static int getDayOfMonth(int year, int month) {
        Calendar c = Calendar.getInstance();
        c.set(year, month, 0);
        return c.get(Calendar.DAY_OF_MONTH);
    }


    /**
     * 获取某年,某月某日的前一天
     *
     * @param year
     * @param month
     * @param day
     * @return
     */
    public static int[] beforeDay(int year, int month, int day) {

        int lastDay = day - 1;
        if (lastDay == 0) {
            int lastMonth = month - 1;
            if (lastMonth == 0) {
                year = year - 1;
                month = 12;
            } else {
                month = lastMonth;
            }
            day = DateParseUtils.getDayOfMonth(year, month);
        } else {
            day = lastDay;
        }
        return new int[]{year, month, day};
    }

    /**
     * 获取某年,某月某日的后一天
     *
     * @param year
     * @param month
     * @param day
     * @return
     */
    public static int[] afterDay(int year, int month, int day) {
        int daySize = DateParseUtils.getDayOfMonth(year, month);
        int afterDay = day + 1;
        if (afterDay == daySize + 1) {
            int afterMonth = month + 1;
            if (afterMonth == 13) {
                year = year + 1;
                month = 1;
            } else {
                month = afterMonth;
            }
            day = 1;
        } else {
            day = afterDay;
        }
        return new int[]{year, month, day};
    }

    /**
     * 获取当前年
     *
     * @return
     */
    public static int getYear() {
        Calendar instance = Calendar.getInstance();
        return instance.get(Calendar.YEAR);
    }

    /**
     * 获取当前月
     *
     * @return
     */
    public static int getMonth() {
        Calendar instance = Calendar.getInstance();
        return instance.get(Calendar.MONTH);
    }

    /**
     * 获取当天几号
     *
     * @return
     */
    public static int getDay() {
        Calendar instance = Calendar.getInstance();
        return instance.get(Calendar.DAY_OF_MONTH);
    }

    /**
     * 获取某天是星期几
     *
     * @param year
     * @param month
     * @param day
     * @return
     */
    public static int getWeek(int year, int month, int day) {
        Calendar instance = Calendar.getInstance();
        instance.set(Calendar.YEAR, year);
        instance.set(Calendar.MONTH, month);
        instance.set(Calendar.DAY_OF_MONTH, day);
        return instance.get(Calendar.DAY_OF_WEEK);
//
//        case 1:    
//   weekTextView.setText("星期日");    
//   break;    
//case 2:    
//   weekTextView.setText("星期一");    
//   break;    
//case 3:    
//   weekTextView.setText("星期二");    
//   break;    
//case 4:    
//   weekTextView.setText("星期三");    
//   break;    
//case 5:    
//   weekTextView.setText("星期四");    
//   break;    
//case 6:    
//   weekTextView.setText("星期五");    
//   break;    
//case 7:    
//   weekTextView.setText("星期六");    
//   break;  

    }

    /**
     * 获取某天是星期几
     *
     * @param year
     * @param month 月份是从0开始的
     * @param day
     * @return
     */
    public static String getWeekStr(int year, int month, int day) {
        Calendar instance = Calendar.getInstance();
        instance.set(Calendar.YEAR, year);
        instance.set(Calendar.MONTH, month);
        instance.set(Calendar.DAY_OF_MONTH, day);
        int i = instance.get(Calendar.DAY_OF_WEEK);
        switch (i) {
            case 1:
                return "星期日";
            case 2:
                return "星期一";
            case 3:
                return "星期二";
            case 4:
                return "星期三";
            case 5:
                return "星期四";
            case 6:
                return "星期五";
            case 7:
                return "星期六";
            default:
                return "";
        }
    }

    /**
     * 获取星期几
     *
     * @return
     */
    public static String getWeek() {
        Calendar cal = Calendar.getInstance();
        int i = cal.get(Calendar.DAY_OF_WEEK);
        switch (i) {
            case 1:
                return "星期日";
            case 2:
                return "星期一";
            case 3:
                return "星期二";
            case 4:
                return "星期三";
            case 5:
                return "星期四";
            case 6:
                return "星期五";
            case 7:
                return "星期六";
            default:
                return "";
        }
    }

    /**
     * 获取n天前的数据
     *eg:前一天  -1
     * 后一天 1
     * @param n
     */
    public static String getBeforeTime(int n) {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DATE, n);
        return getDateToString(calendar.getTime(), pattern);
    }

}
