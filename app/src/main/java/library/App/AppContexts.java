package library.App;

import android.app.Application;
import android.content.Context;
import android.support.multidex.MultiDex;

import com.dwz.mvvmdemo.commom.di.component.AppComponent;
import com.dwz.mvvmdemo.commom.di.component.DaggerAppComponent;
import com.dwz.mvvmdemo.commom.di.component.DaggerDPComponent;
import com.dwz.mvvmdemo.commom.di.module.AppModule;
import com.dwz.mvvmdemo.commom.di.module.DApiModule;

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
    private static AppContexts instance;
    private AppComponent appComponent;
    @Override
    public void onCreate() {
        super.onCreate();
        //处理方法数量超过
        MultiDex.install(this);
        CrashHandler.getInstance().init(this);
        AppContext = getApplicationContext();
        instance = this;
        sScale =  getResources().getDisplayMetrics().density;
        heightPixels = getResources().getDisplayMetrics().heightPixels;
        widthPixels = getResources().getDisplayMetrics().widthPixels;
        initCompoent();
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }


    public static Context App(){
        return AppContext;
    }

    private void initCompoent() {
        appComponent = DaggerAppComponent.builder()
                .dApiModule(new DApiModule())
                .appModule(new AppModule(this))
                .build();
    }

    public AppComponent getAppComponent() {
        return appComponent;
    }

    public static AppContexts getInstance(){
        return instance;
    }
}
