package com.dwz.library.App;

import android.app.Activity;

import com.dwz.library.MainActivity;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


/**
 * Activity管理类，可用于一键关闭应用
 * 每个Activity应继承BaseActivity
 */
public class AppManager {

    public List<Activity> activitys = new ArrayList<>();
    private static AppManager instance = null;

    /***
     * 单一实例
     */
    public static AppManager getAppManager() {
        if (instance == null) {
            synchronized (AppManager.class) {
                if (instance == null) {
                    instance = new AppManager();
                }
            }
        }

        return instance;
    }

    /**
     * 添加activity
     *
     * @param activity
     */
    public void activityCreated(Activity activity) {
        activitys.add(activity);
    }

    /**
     * 删除activity
     *
     * @param activity
     */
    public void activityDestroyed(Activity activity) {
        activitys.remove(activity);
    }

    /***
     * 获取当前Activity
     */
    public Activity currentActivity() {
        if (activitys != null) {
            int size = activitys.size();
            if (size > 0) {
                return activitys.get(size - 1);
            }
        }

        return null;
    }

    /**
     * 关闭所有页面
     */
    public void finishAllActivity() {
        if (activitys.size() != 0) {
            Iterator<Activity> aIterator = activitys.iterator();
            while (aIterator.hasNext()) {
                Activity activity = aIterator.next();
                if (activity != null && !activity.isFinishing()) {
                    activity.finish();
                }
            }
        }
        activitys.clear();
    }

    /**
     * 删除所有activity 除了 MainActivity
     */
    public void exitExceptMain() {
        if (activitys.size() != 0) {
            Iterator<Activity> aIterator = activitys.iterator();
            while (aIterator.hasNext()) {
                Activity activity = aIterator.next();
                if (activity != null && !activity.isFinishing()) {
                    if (activity instanceof MainActivity) {
                    } else {
                        activity.finish();
                    }
                }
            }
        }
    }

    /**
     * 退出应用程序
     */
    public void exitApp() {
        finishAllActivity();
        android.os.Process.killProcess(android.os.Process.myPid());
        System.exit(0);
        System.gc();
    }
}
