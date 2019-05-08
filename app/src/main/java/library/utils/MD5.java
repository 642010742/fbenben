package library.utils;


import android.util.Log;

import java.security.MessageDigest;

public class MD5 {
	public static String sv = "Ix4c$S732nqix*cH";
	public final static String MD5(String s) {
		char hexDigits[]={'0','1','2','3','4','5','6','7','8','9','A','B','C','D','E','F'};
		try {
			byte[] btInput = s.getBytes();
			// 获得MD5摘要算法的 MessageDigest 对象
			MessageDigest mdInst = MessageDigest.getInstance("MD5");
			// 使用指定的字节更新摘要
			mdInst.update(btInput);
			// 获得密文
			byte[] md = mdInst.digest();
			// 把密文转换成十六进制的字符串形式
			int j = md.length;
			char str[] = new char[j * 2];
			int k = 0;
			for (int i = 0; i < j; i++) {
				byte byte0 = md[i];
				str[k++] = hexDigits[byte0 >>> 4 & 0xf];
				str[k++] = hexDigits[byte0 & 0xf];
			}
			return new String(str);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public static String Md5_(String str){
		String str2 = MD5(str);
		if(str2.length() > 0) {
			str2 += sv;
			str2 = MD5(str2);
		}
		Log.i("Setting","Md5_ ==>"+str2);
		return str2.substring(8,24);
	}
}
