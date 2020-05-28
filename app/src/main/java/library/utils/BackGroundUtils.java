package library.utils;

import android.app.Activity;
import android.database.ContentObserver;
import android.provider.Settings;
import android.view.Window;
import android.view.WindowManager;

import library.App.AppConstants;
import library.App.AppContexts;

/**
 * @author Administrator
 * @Create 2019/4/1
 * @Description TODO
 * @zmf
 */
public class BackGroundUtils {

    /**
     * 修改背景透明度
     * @param activity
     * @param bgAlpha
     */
    public static void backgroundAlpha(Activity activity, float bgAlpha) {
        WindowManager.LayoutParams lp = activity.getWindow().getAttributes();
        lp.alpha = bgAlpha;
        activity.getWindow().setAttributes(lp);
    }

    /**
     * 获取系统亮度
     *
     * @return
     */
    public static int getSystemBrightness() {
        int systemBrightness = 0;
        try {
            systemBrightness = Settings.System.getInt(AppContexts.App().getContentResolver(), Settings.System.SCREEN_BRIGHTNESS);
        } catch (Settings.SettingNotFoundException e) {
            e.printStackTrace();
        }
        return systemBrightness;
    }

    /**
     * 设置系统亮度
     *设置页面亮度
     * @param activity
     */
    public static void changeAppBrightness(Activity activity, int brightness) {
        Window window = activity.getWindow();
        WindowManager.LayoutParams lp = window.getAttributes();
        if (brightness == -1) {
            lp.screenBrightness = WindowManager.LayoutParams.BRIGHTNESS_OVERRIDE_NONE;
        } else {
            lp.screenBrightness = (brightness <= 0 ? 1 : brightness) / 255f;
        }
        window.setAttributes(lp);
    }

//    2.屏幕亮度监测  该方法有用,只是暂时卸载这里
//
//    在BaseActivity里面onResume(),里面注册监听
//    getContentResolver().registerContentObserver(
//            Settings.System.getUriFor(Settings.System.SCREEN_BRIGHTNESS), true,
//    mBrightnessObserver);
//
//    在onDestory里面取消监听
//    getContentResolver().unregisterContentObserver(
//            mBrightnessObserver);
//
//    private ContentObserver mBrightnessObserver = new ContentObserver(new Handler()) {
//        @Override
//        public void onChange(boolean selfChange) {
//            int systemBrightness = BackGroundUtils.getSystemBrightness();
//            BackGroundUtils.changeAppBrightness(BaseActivity.this, AppConstants.nightMode ? systemBrightness / 2 : systemBrightness);
//        }
//    };
}
