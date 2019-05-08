package library.utils;

import android.widget.Toast;

import library.App.AppManager;

/**
 *
 */
public class ToastUtil {

    private static long startTime;
    private static int second = 1000;


    public static void showShort(String msg) {
        if (AppManager.getAppManager().currentActivity() != null) {
            long endTime = System.currentTimeMillis();
            if ((endTime - startTime) > second) {
                startTime = endTime;
                Toast mToast = Toast.makeText(AppManager.getAppManager().currentActivity(), null, Toast.LENGTH_SHORT);
                mToast.setText(msg);
                mToast.show();
            }
        }
    }

    public static void showShort(int msgId) {
        if (AppManager.getAppManager().currentActivity() != null) {
            long endTime = System.currentTimeMillis();
            if ((endTime - startTime) > second) {
                startTime = endTime;
                Toast.makeText(AppManager.getAppManager().currentActivity(), msgId, Toast.LENGTH_SHORT).show();
            }
        }
    }


    public static void showLong(String msg) {
        if (AppManager.getAppManager().currentActivity() != null) {
            long endTime = System.currentTimeMillis();
            if ((endTime - startTime) > second) {
                startTime = endTime;
                Toast.makeText(AppManager.getAppManager().currentActivity(), msg, Toast.LENGTH_LONG).show();
            }
        }
    }

    public static void showLong(int msgId) {
        if (AppManager.getAppManager().currentActivity() != null) {
            long endTime = System.currentTimeMillis();
            if ((endTime - startTime) > second) {
                startTime = endTime;
                Toast.makeText(AppManager.getAppManager().currentActivity(), msgId, Toast.LENGTH_LONG).show();
            }
        }
    }
}
