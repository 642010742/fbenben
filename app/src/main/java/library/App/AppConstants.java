package library.App;

/**
 * 常量类
 */
public class AppConstants {

    public static final String[] NEW_URL = {"fragrantLight", "cameraLight", "smog"};
    public static String selectDownPrice;
    public static String selectUpPrice;
    public static final int CODE_GALLERY_REQUEST = 0xa0;
    public static final int CODE_CAMERA_REQUEST = 0xa1;
    public static final int CODE_RESULT_REQUEST = 0xa2;
    public static double defTotal = 23 * 1024 * 1024;

    public class IntentKey {
        //0-分类搜索 1-首页搜索
        public static final String from = "from";
        public static final String WEB_URL = "WEB_URL";
        public static final String WEB_TITLE = "WEB_TITLE";
    }

    public class EventKey {
        //评论点击增加图片
        public static final int defaultImg = 00000001;
    }
}
