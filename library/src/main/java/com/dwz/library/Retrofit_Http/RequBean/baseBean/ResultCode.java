package com.dwz.library.Retrofit_Http.RequBean.baseBean;

/***
 * 结果code
 * @author Administrator
 *
 */
public class ResultCode {
	// 数据请求成功返回码 
	public static final int RESULT_OK = 0;
	// 数据请求失败 
	public static final int RESULT_ERROR = -1;
		
	// 服务器返回成功，数据为空
	public static final int RESULT_DATA_EMPTY = 210;
	// 无可用网络
	public static final int NETWORK_INVALID = 61;
	// 无请求方式
	public static final int REQUEST_TYPE_ERROR = 60;
	// 链接超时
	public static final int CONNECT_TIMEOUT = -1000;
	// 数据请求异常
	public static final int RESULT_FAILE = -1001;
}
