package com.dwz.library.App;

/**
 * 常量类
 */
public class AppConstants {

    public static final String[] NEW_URL = {"fragrantLight", "cameraLight", "smog"};
    public static final int REQUEST_CODE_CHOOSE = 0xa1;
    public static double defTotal = 23 * 1024 * 1024;

    public class IntentKey {
        //0-分类搜索 1-首页搜索
        public static final String from = "from";
        public static final String WEB_URL = "WEB_URL";
        public static final String WEB_TITLE = "WEB_TITLE";
    }

    public class EventKey {
        //评论点击增加图片
        public static final int aaa = 00000001;
    }
}
