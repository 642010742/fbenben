package com.dwz.library.App;

import okhttp3.MediaType;

public class HttpConstants {
    public static boolean aBoolean;

    public final static String TAG = "RxHttpClient-------->";
    public final static String METHOD_GET = "GET";
    public final static String METHOD_POST = "POST";
    public final static String METHOD_PUT = "PUT";
    public final static String METHOD_DELETE = "DELETE";
    //	public final static MediaType JSON = MediaType.parse("application/x-www-form-urlencoded");
    public final static MediaType JSON = MediaType.parse("application/json;");
    public final static boolean ISDEBUG = true;


    /**
     * 地址请求头
     */
    public static String BASE_DEV_URL = "";

}
