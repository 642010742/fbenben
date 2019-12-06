package library.utils.upapk;

import java.text.DecimalFormat;

/**
 * Created by Administrator on 2018/2/3.
 */

public class NumberFormatUtil {

    /**
     * 保留两位小数
     *
     * @param num
     * @return
     */
    public static String twoDecimal(double num) {
        DecimalFormat df = new DecimalFormat("0.00");
//        String format = df.format(num);
//        String substring = format.substring(format.length() - 2, format.length());
//        String substring1 = format.substring(0, format.length() - 3);
//        if (TextUtils.equals("00", substring)) {
//            return substring1;
//        }
        return df.format(num);
    }

    /**
     * 保留一位小数
     *
     * @param num
     * @return
     */
    public static String oneDecimal(double num) {
        DecimalFormat df = new DecimalFormat("0.0");
//        String format = df.format(num);
//        String substring = format.substring(format.length() - 2, format.length());
//        String substring1 = format.substring(0, format.length() - 3);
//        if (TextUtils.equals("0", substring)) {
//            return substring1;
//        }
        String format = df.format(num);
        if (format.endsWith(".0")) {
            format = format.replace(".0","");
        }
        return format;
    }
}
