package library.utils;

import android.annotation.TargetApi;
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

import androidx.annotation.RequiresApi;
import androidx.core.app.NotificationCompat;

import com.dwz.mvvmdemo.model.NotifyModel;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

import library.App.AppContexts;

import static android.content.Context.NOTIFICATION_SERVICE;

/**
 * @author Administrator
 * @Create 2019/7/1
 * @Description TODO
 * @zmf
 */
public class NotifyUtils {

    private static final int PUSH_NOTIFICATION_ID = (0x001);
    private static final String PUSH_CHANNEL_ID = "PUSH_NOTIFY_ID";
    private static final String PUSH_CHANNEL_NAME = "PUSH_NOTIFY_NAME";

    /**
     * 查看是否允许通知栏显示消息
     *
     * @param context
     * @return
     */
    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public static boolean isNotificationEnabled(Context context) {

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
     * @param pendingIntent
     */
    public void creatNotify(PendingIntent pendingIntent) {
        NotifyModel notifyModel = new NotifyModel();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            createNotificationChannel(notifyModel, pendingIntent);
        } else {
            showNotification(notifyModel, pendingIntent);
        }
    }

    public void showNotification(NotifyModel notifyMode, PendingIntent pendingIntent) {
        NotificationManager notifcationManage = (NotificationManager) AppContexts.App().getSystemService(NOTIFICATION_SERVICE);
        NotificationCompat.Builder build = new NotificationCompat.Builder(AppContexts.App(), "default");
        build.setContentTitle(notifyMode.getTitle());//设置通知栏标题
        build.setContentText(notifyMode.getContent()); //设置通知栏显示内容
        build.setContentIntent(pendingIntent);//设置通知栏点击意图
        //build.setNumber(3); //设置通知集合的数量   
        build.setTicker("有新消息"); //通知首次出现在通知栏，带上升动画效果的     
        build.setWhen(System.currentTimeMillis());//通知产生的时间，会在通知信息里显示，一般是系统获取到的时间
        build.setPriority(Notification.PRIORITY_DEFAULT); //设置该通知优先级
        build.setAutoCancel(true);//设置这个标志当用户单击面板就可以让通知将自动取消
//        build.setOngoing(false);//ture，设置他为一个正在进行的通知,通常是用来表示一个后台任务,以某种方式正在等待,如一个文件下载,同步操作
        build.setDefaults(Notification.DEFAULT_VIBRATE);//向通知添加声音、闪灯和振动效果
        build.setSmallIcon(notifyMode.getSmallIcon());//设置通知小ICON
        build.setLargeIcon(BitmapFactory.decodeResource(AppContexts.App().getResources(), notifyMode.getLargeIcon()));
        Notification mNotification = build.build();
        notifcationManage.notify(PUSH_NOTIFICATION_ID, mNotification);
    }


    @TargetApi(Build.VERSION_CODES.O)
    private void createNotificationChannel(NotifyModel notifyModel,PendingIntent pendingIntent) {

        NotificationManager manager = (NotificationManager) AppContexts.App().getSystemService(NOTIFICATION_SERVICE);
        NotificationChannel channel = new NotificationChannel(
                PUSH_CHANNEL_ID,
                PUSH_CHANNEL_NAME,
                NotificationManager.IMPORTANCE_DEFAULT);
        manager.createNotificationChannel(channel);

        Notification notification = new NotificationCompat.Builder(AppContexts.App(), PUSH_CHANNEL_ID)
                .setContentTitle(notifyModel.getTitle())
                .setContentText(notifyModel.getContent())
                .setChannelId(PUSH_CHANNEL_ID)
                .setWhen(System.currentTimeMillis())
                .setSmallIcon(notifyModel.getSmallIcon())
                .setLargeIcon(BitmapFactory.decodeResource(AppContexts.App().getResources(), notifyModel.getLargeIcon()))
                .setContentIntent(pendingIntent)
                .setWhen(System.currentTimeMillis())//通知产生的时间，会在通知信息里显示，一般是系统获取到的时间
                .setDefaults(Notification.DEFAULT_ALL)//向通知添加声音、闪灯和振动效果的最简单、最一致的方式是使用当前的用户默认设置，使用defaults属性，可以组合
                .setAutoCancel(true)
                .build();
        manager.notify(PUSH_NOTIFICATION_ID, notification);
    }
}
