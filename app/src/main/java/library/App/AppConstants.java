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

    // 通知购物车fragment 请求数据

    public static final int REFRESH = 0xa3;

    public class IntentKey {
        //0-分类搜索 1-首页搜索 2-订单搜索 3-我的收藏搜索 4-我的关注搜索 5-商家信息 6-新增地址 7-编辑地址
        //8-确认订单 9-地址管理
        public static final String from = "from";
    }

    public class EventKey{
        //评论点击增加图片
        public static final int defaultImg = 00000001;
        //我的足迹child  item点击
        public static final int itemChildClick = 00000002;
    }
}
