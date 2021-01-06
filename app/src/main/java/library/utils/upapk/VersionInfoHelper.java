package library.utils.upapk;

import android.app.Activity;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;

public class VersionInfoHelper {

    private static Activity mActivity;
    private static VersionInfoHelper versionInfoHelper;

    public static VersionInfoHelper getInstance(Activity activity) {
        mActivity = activity;
        if (versionInfoHelper == null) {
            versionInfoHelper = new VersionInfoHelper();
        }
        return versionInfoHelper;

    }

    public int getVersionCode() {
        PackageManager packageManager = mActivity.getPackageManager();
        try {
            PackageInfo packageInfo = packageManager.getPackageInfo(mActivity.getPackageName(), 0);
            return packageInfo.versionCode;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public String getVersionName(){
        PackageManager packageManager = mActivity.getPackageManager();
        try {
            PackageInfo packageInfo = packageManager.getPackageInfo(mActivity.getPackageName(), 0);
            return packageInfo.versionName;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
            return "1.0.0";
        }
    }
}
