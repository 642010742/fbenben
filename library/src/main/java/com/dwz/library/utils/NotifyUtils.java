package com.dwz.library.utils;

import android.app.Activity;
import android.app.AppOpsManager;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.provider.Settings;

import com.dwz.library.App.AppContexts;
import com.dwz.library.R;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import androidx.annotation.RequiresApi;
import androidx.core.app.NotificationCompat;
import static android.content.Context.NOTIFICATION_SERVICE;

/**
 * @author Administrator
 * @Create 2019/7/1
 * @Description TODO
 * @zmf
 */
public class NotifyUtils {
    private static NotifyUtils notifyUtils;
    //每个通知指定的id  保证每个通知指定的id是不同的
    private static final int PUSH_NOTIFICATION_ID = 1;
    //渠道id  随便定义 保证全局唯一性
    private static final String PUSH_CHANNEL_ID = "PUSH_NOTIFY_ID";
    //渠道名称 给用户查看 清楚表示渠道用途
    private static final String PUSH_CHANNEL_NAME = "PUSH_NOTIFY_NAME";

    public static NotifyUtils getInstance() {
        if (notifyUtils == null) {
            notifyUtils = new NotifyUtils();
        }
        return notifyUtils;
    }

    /**
     * 查看是否允许通知栏显示消息
     *
     * @param context
     * @return
     */
    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public boolean isNotificationEnabled(Context context) {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            //8.0手机以上
            if (((NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE)).getImportance() == NotificationManager.IMPORTANCE_NONE) {
                return false;
            }
        }

        String CHECK_OP_NO_THROW = "checkOpNoThrow";
        String OP_POST_NOTIFICATION = "OP_POST_NOTIFICATION";
        AppOpsManager mAppOps = (AppOpsManager) context.getSystemService(Context.APP_OPS_SERVICE);
        ApplicationInfo appInfo = context.getApplicationInfo();
        String pkg = context.getApplicationContext().getPackageName();
        int uid = appInfo.uid;
        Class appOpsClass = null;
        try {
            appOpsClass = Class.forName(AppOpsManager.class.getName());
            Method checkOpNoThrowMethod = appOpsClass.getMethod(CHECK_OP_NO_THROW, Integer.TYPE, Integer.TYPE,
                    String.class);
            Field opPostNotificationValue = appOpsClass.getDeclaredField(OP_POST_NOTIFICATION);
            int value = (Integer) opPostNotificationValue.get(Integer.class);
            return ((Integer) checkOpNoThrowMethod.invoke(mAppOps, value, uid, pkg) == AppOpsManager.MODE_ALLOWED);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }


    /**
     * 跳转至系统设置页面
     *
     * @param activity
     */
    public void toSetActivity(Activity activity) {
        Intent intent = new Intent(Settings.ACTION_SETTINGS);
        activity.startActivity(intent);
    }

    /**
     * 创建通知
     *
     * @param intent
     */
    public void creatNotify(String title, String content, Intent intent) {
        PendingIntent pendingIntent = null;
        if (intent != null) {
            pendingIntent = PendingIntent.getActivity(
                    AppContexts.App(), 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
        }
        NotificationManager manager = (NotificationManager) AppContexts.App().getSystemService(NOTIFICATION_SERVICE);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel(
                    PUSH_CHANNEL_ID,
                    PUSH_CHANNEL_NAME,
                    NotificationManager.IMPORTANCE_DEFAULT);//初始化等级，用户可进行修改
            manager.createNotificationChannel(channel);
        }

        NotificationCompat.Builder build = new NotificationCompat.Builder(AppContexts.App(), PUSH_CHANNEL_ID);
        build.setContentTitle(title);//设置通知栏标题
        build.setContentText(content); //设置通知栏显示内容
        if (pendingIntent != null) {
            //设置通知栏点击意图
            build.setContentIntent(pendingIntent);
        }
        //build.setNumber(3); //设置通知集合的数量   
//        build.setTicker("有新消息"); //通知首次出现在通知栏，带上升动画效果的     
        build.setWhen(System.currentTimeMillis());//通知产生的时间，会在通知信息里显示，一般是系统获取到的时间
        build.setPriority(Notification.PRIORITY_DEFAULT); //设置该通知优先级
        build.setAutoCancel(true);//设置这个标志当用户单击面板就可以让通知将自动取消
//        build.setOngoing(false);//ture，设置他为一个正在进行的通知,通常是用来表示一个后台任务,以某种方式正在等待,如一个文件下载,同步操作
        build.setDefaults(Notification.DEFAULT_VIBRATE);//向通知添加声音、闪灯和振动效果
        build.setSmallIcon(R.mipmap.ic_launcher);//设置通知小ICON
        build.setLargeIcon(BitmapFactory.decodeResource(AppContexts.App().getResources(), R.mipmap.ic_launcher));
        Notification mNotification = build.build();
        manager.notify(PUSH_NOTIFICATION_ID, mNotification);
    }
}
