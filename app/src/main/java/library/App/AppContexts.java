package library.App;

import android.app.Application;
import android.content.Context;
import android.support.multidex.MultiDex;

import library.utils.CrashHandler;

/**
 * 管理app生命周期
 */
public class AppContexts extends Application {
    // 屏幕分辨率
    public static float sScale;
    // 屏幕宽高
    public static int heightPixels,widthPixels;
    private static Context AppContext;
    @Override
    public void onCreate() {
        super.onCreate();
        //处理方法数量超过
        MultiDex.install(this);
        CrashHandler.getInstance().init(this);
        AppContext = getApplicationContext();
        sScale =  getResources().getDisplayMetrics().density;
        heightPixels = getResources().getDisplayMetrics().heightPixels;
        widthPixels = getResources().getDisplayMetrics().widthPixels;
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }


    public static Context App(){
        return AppContext;
    }
}
