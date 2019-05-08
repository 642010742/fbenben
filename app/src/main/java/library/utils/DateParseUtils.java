package library.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * 时间字符串与时间戳 互转
 */

public class DateParseUtils {

    public static String getDateToString(long time) {
        Date d = new Date(time);
        SimpleDateFormat sf = new SimpleDateFormat("yyyy/MM/dd HH:mm");
        return sf.format(d);
    }
    public static String getDateToString12(long time) {
        Date d = new Date(time);
        SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        return sf.format(d);
    }

    public static String getDateToString5(long time) {
        Date d = new Date(time);
        SimpleDateFormat sf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        return sf.format(d);
    }

    public static String getDateToString15(long time) {
        Date d = new Date(time);
        SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return sf.format(d);
    }

    public static String getDateToString4(long time) {
        Date d = new Date(time);
        SimpleDateFormat sf = new SimpleDateFormat("MM-dd HH:mm");
        return sf.format(d);
    }

    public static String getDateToString2(long time) {
        Date d = new Date(time);
        SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
        return sf.format(d);
    }
    public static String getDateToString9(long time) {
        Date d = new Date(time);
        SimpleDateFormat sf = new SimpleDateFormat("yyyy/MM/dd");
        return sf.format(d);
    }
    public static String getDateToString10(long time) {
        Date d = new Date(time);
        SimpleDateFormat sf = new SimpleDateFormat("yyyy年MM月dd日");
        return sf.format(d);
    }

    public static String getDateToString3(long time) {
        DateFormat formatter = new SimpleDateFormat("mm:ss");
        return formatter.format(new Date(time));
    }
    public static String getDateToString6(long time) {
        DateFormat formatter = new SimpleDateFormat("MM-dd HH:mm:ss");
        return formatter.format(new Date(time));
    }
    public static String getDateToString7(long time) {
        DateFormat formatter = new SimpleDateFormat("HH:mm");
        return formatter.format(new Date(time));
    }
    public static String getDateToString8(long time) {
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

    public static String getDateToString11(long time) {
        Date d = new Date(time);
        SimpleDateFormat sf = new SimpleDateFormat("yyyy.MM.dd");
        return sf.format(d);
    }

    public static String getDayToString(long time){
        Date d = new Date(time);
        SimpleDateFormat sf = new SimpleDateFormat("dd");
        return sf.format(d);
    }

    public static String getEnglishMonth(long time){
        Date d = new Date(time);
        SimpleDateFormat sf = new SimpleDateFormat("MM");
        String month = sf.format(d);
        String monthText = "";
        switch (month){
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
     * @param time 2017年4月1日
     * @return
     */
    public static long getTimestamp(String time){
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
        int timeStr = (int) (time % (24*60*60*1000));
        int hour = timeStr / (60*60*1000);
        int min = (timeStr % (60*60*1000)) / (60*1000);
        int second = (timeStr % (60*60*1000)) % (60*1000) / 1000;
        String hourStr;
        String minStr;
        String secondStr;
        if (hour < 10){
            hourStr = "0"+hour;
        }else{
            hourStr = hour+"";
        }
        if (min < 10){
            minStr = "0"+min;
        }else{
            minStr = min+"";
        }
        if (second < 10){
            secondStr = "0"+second;
        }else{
            secondStr = second+"";
        }
        return hourStr+":"+minStr+":"+secondStr;
    }

    public static long getCurrentTimestamp(){
        return System.currentTimeMillis();
    }
}
