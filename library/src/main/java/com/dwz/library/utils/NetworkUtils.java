package com.dwz.library.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.telephony.TelephonyManager;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * 网络状态辅助类
 * 
 * @author
 * 
 */
public class NetworkUtils {

	/**
	 * 
	 * 获取当前网络连接的类型信息
	 * 
	 * @param context
	 * @return 返回类型为1,ConnectivityManager.TYPE_MOBILE;
	 *         2,ConnectivityManager.TYPE_WIFI;
	 */

	public  static final int NETWORK_NONE = -1;
	public static final int NETWORK_MOBILE = 0;
	public static final int NETWORK_WIFI = 1;

	public static int getNetWorkState(Context context) {
		// 得到连接管理器对象
		ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
		if (activeNetworkInfo != null && activeNetworkInfo.isConnected()) {

			if (activeNetworkInfo.getType() == (ConnectivityManager.TYPE_WIFI)) {
				return NETWORK_WIFI;
			} else if (activeNetworkInfo.getType() == (ConnectivityManager.TYPE_MOBILE)) {
				return NETWORK_MOBILE;

			}

		} else {

			return NETWORK_NONE;
		}

		return NETWORK_NONE;

	}

	public static String getConnectedNetType(Context context) {
		String type = "";
		if (null == context) {
			return null;
		}

		ConnectivityManager mConnectivityManager = (ConnectivityManager) context
				.getSystemService(Context.CONNECTIVITY_SERVICE);

		NetworkInfo mNetworkInfo = mConnectivityManager.getActiveNetworkInfo();

		if (mNetworkInfo != null && mNetworkInfo.isConnected()) {
			if (mNetworkInfo.getType() == ConnectivityManager.TYPE_MOBILE) {
				type = getNetworkType(mNetworkInfo.getSubtype()) + "-"
						+ mNetworkInfo.getExtraInfo() + "-"
						+ mNetworkInfo.getSubtypeName();
			} else {
				type = mNetworkInfo.getTypeName();
			}
		}

		return type.trim();
	}

	/***
	 * 判断是否为wap模式
	 * 
	 * @param context
	 * @return
	 */
	public static boolean isWapModel(Context context) {
		boolean res = false;
		if (context != null) {
			ConnectivityManager mConnectivityManager = (ConnectivityManager) context
					.getSystemService(Context.CONNECTIVITY_SERVICE);
			NetworkInfo mNetworkInfo = mConnectivityManager
					.getActiveNetworkInfo();

			if (mNetworkInfo != null && mNetworkInfo.isConnected()) {
				if (mNetworkInfo.getType() == ConnectivityManager.TYPE_MOBILE) {
					String netMode = mNetworkInfo.getExtraInfo();
					if (netMode != null && netMode.contains("wap")) {
						res = true;
					}
				}
			}
		}

		return res;
	}

	public static String getNetworkType(int networkType) {
		String type = "";
		switch (networkType) {
		case TelephonyManager.NETWORK_TYPE_GPRS:
		case TelephonyManager.NETWORK_TYPE_EDGE:
		case TelephonyManager.NETWORK_TYPE_CDMA:
		case TelephonyManager.NETWORK_TYPE_1xRTT:
		case TelephonyManager.NETWORK_TYPE_IDEN:
			type = "2G";
			break;

		case TelephonyManager.NETWORK_TYPE_UMTS:
		case TelephonyManager.NETWORK_TYPE_EVDO_0:
		case TelephonyManager.NETWORK_TYPE_EVDO_A:
		case TelephonyManager.NETWORK_TYPE_HSDPA:
		case TelephonyManager.NETWORK_TYPE_HSUPA:
		case TelephonyManager.NETWORK_TYPE_HSPA:
		case TelephonyManager.NETWORK_TYPE_EVDO_B:
		case TelephonyManager.NETWORK_TYPE_EHRPD:
		case TelephonyManager.NETWORK_TYPE_HSPAP:
			type = "3G";
			break;

		case TelephonyManager.NETWORK_TYPE_LTE:
			type = "4G";
			break;
		}

		return type;
	}

	/**
	 * 判断是否有网络连接
	 * 
	 * @param context
	 * @return
	 */
	public static boolean isNetworkConnected(Context context) {
		if (context != null) {
			ConnectivityManager mConnectivityManager = (ConnectivityManager) context
					.getSystemService(Context.CONNECTIVITY_SERVICE);
			NetworkInfo mNetworkInfo = mConnectivityManager
					.getActiveNetworkInfo();
			if (mNetworkInfo != null) {
				return mNetworkInfo.isConnected();// isConnected表示连接上,isAvailable表示可用
			}
		}
		return false;
	}

	/***
	 * 判断是否有可用网络
	 * 
	 * @param context
	 * @return
	 */
	public static boolean isNetworkAvailable(Context context) {
		if (context != null) {
			ConnectivityManager connectivity = (ConnectivityManager) context
					.getSystemService(Context.CONNECTIVITY_SERVICE);
			if (connectivity == null) {
				return false;
			} else {
				NetworkInfo[] info = connectivity.getAllNetworkInfo();
				if (info != null) {
					for (int i = 0; i < info.length; i++) {
						if (info[i].getState() == NetworkInfo.State.CONNECTED) {
							return true;
						}
					}
				}
			}
		}

		return false;
	}

	/**
	 * 判断WIFI网络是否可用
	 * 
	 * @param context
	 * @return
	 */
	public static boolean isWifiConnected(Context context) {
		if (context != null) {
			ConnectivityManager mConnectivityManager = (ConnectivityManager) context
					.getSystemService(Context.CONNECTIVITY_SERVICE);
			NetworkInfo mWiFiNetworkInfo = mConnectivityManager
					.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
			if (mWiFiNetworkInfo != null) {
				return mWiFiNetworkInfo.isConnected();// isConnected表示连接上,isAvailable表示可用
			}
		}
		return false;
	}

	/***
	 * 是否是wifi
	 * 
	 * @return
	 */
	public static Boolean isWifi() {
		// ConnectivityManager connectMgr = (ConnectivityManager)
		// AppContext.mContext
		// .getSystemService(Context.CONNECTIVITY_SERVICE);
		//
		// NetworkInfo info = connectMgr.getActiveNetworkInfo();
		// if (info != null) {
		// return info.getType() == ConnectivityManager.TYPE_WIFI;
		// } else {
		return false;
		// }
	}

	/**
	 * 判断MOBILE网络是否可用
	 * 
	 * @param context
	 * @return
	 */
	public static boolean isMobileConnected(Context context) {
		if (context != null) {
			ConnectivityManager mConnectivityManager = (ConnectivityManager) context
					.getSystemService(Context.CONNECTIVITY_SERVICE);
			NetworkInfo mMobileNetworkInfo = mConnectivityManager
					.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
			if (mMobileNetworkInfo != null) {
				return mMobileNetworkInfo.isConnected();// isConnected表示连接上,isAvailable表示可用
			}
		}
		return false;
	}
	/**
	 * 判断地址是否有效
	 * @param url
	 * @return
	 */
	public static boolean checkURL(String url) {
		boolean value = false;
		try {
			HttpURLConnection conn = (HttpURLConnection) new URL(url)
					.openConnection();
			int code = conn.getResponseCode();
			System.out.println(">>>>>>>>>>>>>>>> " + code
					+ " <<<<<<<<<<<<<<<<<<");
			if (code != 200) {
				value = false;
			} else {
				value = true;
			}
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return value;
	}
}
