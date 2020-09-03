package library.utils.upapk;


import android.app.Activity;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.provider.Settings;
import androidx.annotation.RequiresApi;
import androidx.core.content.FileProvider;
import android.text.Html;
import android.text.TextUtils;
import android.text.method.LinkMovementMethod;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.dwz.mvvmdemo.R;

import java.io.File;

import library.App.AppConstants;
import library.App.AppManager;
import library.listener.BtnDoneListener;
import library.utils.BackGroundUtils;
import library.utils.DialogUtils;
import library.utils.ToastUtil;
import okhttp3.Request;


public class UpdateUtils {

    private static final String appName = "firemen.apk";

    /**
     * 版本更新弹框
     */
//    private static XXAdapter<ImageModel> versionAdapter;
//    private static List<ImageModel> list = new ArrayList<>();
    public static void showUpDataDialog(final Activity activity, int isFource, String content,
                                        final BtnDoneListener btnDoneListener) {

        final Dialog dialog = new Dialog(activity, R.style.MyDialog);
        View view = LayoutInflater.from(activity).
                inflate(R.layout.upversion_dialog_layout, null, false);

        dialog.setContentView(view);
        Window dialogWindow = dialog.getWindow();
        WindowManager m = (WindowManager) activity.getSystemService(Context.WINDOW_SERVICE);
        Display d = m.getDefaultDisplay(); // 获取屏幕宽、高用
        WindowManager.LayoutParams p = dialogWindow.getAttributes(); // 获取对话框当前的参数值
        p.width = (int) (d.getWidth() * 0.8); // 宽度设置为屏幕的0.65
        p.height = p.WRAP_CONTENT;
        dialog.setCanceledOnTouchOutside(false);
        dialog.setCancelable(false);
        BackGroundUtils.backgroundAlpha(activity, 0.5f);

//        RecyclerView listView = (RecyclerView) view.findViewById(R.id.listView);
//        list.clear();
//        ImageModel imageModel = new ImageModel();
//        imageModel.setUrl(updateInfo.getUpContent());
//        list.add(imageModel);
//        if (versionAdapter == null) {
//            versionAdapter = new XXAdapter<>(list, activity);
//            SingleItemView singleItemView = new SingleItemView(R.layout.item_upversion, BR.item);
//            versionAdapter.addItemViewDelegate(singleItemView);
//        }
//        listView.setAdapter(versionAdapter);

        LinearLayout upVersionLayout = view.findViewById(R.id.upVersionLayout);
        TextView upVersionForce = view.findViewById(R.id.upVersionForce);
        TextView upContent = view.findViewById(R.id.upContent);

        //isFource  0-是 1-否
        upVersionLayout.setVisibility(0 == isFource ? View.GONE : View.VISIBLE);
        upVersionForce.setVisibility(0 == isFource ? View.VISIBLE : View.GONE);
        upContent.setText(TextUtils.isEmpty(content) ? "" : Html.fromHtml(content));
        upContent.setMovementMethod(LinkMovementMethod.getInstance());

        view.findViewById(R.id.upVersion).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                btnDoneListener.done("");
            }
        });
        view.findViewById(R.id.upVersionForce).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                btnDoneListener.done("");

            }
        });
        view.findViewById(R.id.noUpVersion).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        dialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
            @Override
            public void onDismiss(DialogInterface dialog) {
                BackGroundUtils.backgroundAlpha(activity, 1.0f);
            }
        });

        dialog.show();
    }


    public static boolean isNeedUpdate(String version) {

        String newVersionCodeStr = version.replace(".", "");

        try {
            Integer newVersionCode = Integer.valueOf(newVersionCodeStr);
            int versionCode = getVersionCode();

            if (newVersionCode <= versionCode) {
                return false;
            } else {
                return true;
            }
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * 获取包名
     *
     * @return
     */
    public static String getPackageName() {
        try {
            String packageName = AppManager.getAppManager()
                    .currentActivity().getPackageName();
            return packageName;
        } catch (Exception e) {
            e.printStackTrace();
            return "包名未知";
        }
    }

    // 获取当前版本的版本号
    public static String getVersionName() {
        try {
            PackageManager packageManager = AppManager.getAppManager()
                    .currentActivity().getPackageManager();
            PackageInfo packageInfo = packageManager.getPackageInfo(AppManager
                    .getAppManager().currentActivity().getPackageName(), 0);
            return packageInfo.versionName;
        } catch (Exception e) {
            e.printStackTrace();
            return "版本号未知";
        }
    }

    // 获取当前版本的版本号
    public static int getVersionCode() {
        try {
            PackageManager packageManager = AppManager.getAppManager()
                    .currentActivity().getPackageManager();
            PackageInfo packageInfo = packageManager.getPackageInfo(AppManager
                    .getAppManager().currentActivity().getPackageName(), 0);
            return packageInfo.versionCode;
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }


    // 获取当前版本的版本号
    public static String getVersionName1() {
        try {
            PackageManager packageManager = AppManager.getAppManager()
                    .currentActivity().getPackageManager();
            PackageInfo packageInfo = packageManager.getPackageInfo(AppManager
                    .getAppManager().currentActivity().getPackageName(), 0);
            return packageInfo.versionName;
        } catch (Exception e) {
            e.printStackTrace();
            return "1.0";
        }
    }

    public static ProgressDialog pBar;

    public static void downFile(final String url, DownloadManager.ResultCallback callback) {
        pBar = new ProgressDialog(AppManager.getAppManager().currentActivity()); // 进度条，在下载的时候实时更新进度，提高用户友好度
        pBar.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        pBar.setTitle("正在下载");
        pBar.setMessage("请稍候...");
        pBar.setProgress(0);
        pBar.setMax(100);
//        pBar.setCanceledOnTouchOutside(false);
//        pBar.setProgressNumberFormat("%1d /%2d ");
//        pBar.setProgressNumberFormat("");
        pBar.setCancelable(false);
        pBar.show();

        try {
            // downloadFile(url);
            doLoading(url, callback);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            cancelDialogOnError();
            ToastUtil.showShort("下载失败，请先检查您的网络或者读写权限");
            e.printStackTrace();
        }
    }

    private static void doLoading(final String url, DownloadManager.ResultCallback callback) {

        double defTotal = AppConstants.defTotal;
        String absolutePath = Environment.getExternalStorageDirectory().getAbsolutePath();
        DownloadManager.downloadFile(url, absolutePath, appName, callback, defTotal);

//        new DownloadManager.ResultCallback() {
//
//            @Override
//            public void onError(okhttp3.Request request, Exception e) {
//
//            }
//
//            @Override
//            public void onResponse(Object response) {
//                down();
//            }
//
//            @Override
//            public void onProgress(double total, double current) {
//                int proX = (int) (current * 100 / total);
//                pBar.setProgress(proX >= 100 ? 99 : proX); // 这里就是关键的实时更新进度了！
//                pBar.setProgressNumberFormat(NumberFormatUtil.oneDecimal(total / 1024.00f / 1024.00f) + "M");
//            }
//        }, defTotal);
    }

    public static void down() {

        update();
    }

    public static void cancelDialogOnError() {

        if (pBar == null || !pBar.isShowing()) {
            return;
        }
        pBar.cancel();
    }

    // 安装文件，一般固定写法
    static void update() {
        /**
         * 修改apk文件的权限为可执行 ，例如chmod ‘777’ file：解决部分手机问题
         */
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION | Intent.FLAG_ACTIVITY_NEW_TASK);
        Uri newUri;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {

            newUri = FileProvider.getUriForFile(AppManager.getAppManager().currentActivity(), AppManager.getAppManager().currentActivity().getPackageName() + ".demo.fileprovider", new File(Environment
                    .getExternalStorageDirectory(), appName));

        } else {
            newUri = Uri.fromFile(new File(Environment
                    .getExternalStorageDirectory(), appName));
        }
        intent.setDataAndType(newUri,
                "application/vnd.android.package-archive");
        AppManager.getAppManager().currentActivity().startActivity(intent);
//        if (UpdateUtils.isFourceUp) {
//            //强制升级关闭APP
//            AppManager.getAppManager().exitApp();
//        }
    }

    public static void upVersion(final Activity activity, String loadUrl) {
        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
            //下载地址
            if (!TextUtils.isEmpty(loadUrl) && (loadUrl.contains("http") || loadUrl.contains("https"))) {
                UpdateUtils.downFile(loadUrl, new DownloadManager.ResultCallback() {
                    @Override
                    public void onError(Request request, Exception e) {

                    }

                    @Override
                    public void onResponse(Object response) {
                        UpdateUtils.installProcess(activity);
                    }

                    @Override
                    public void onProgress(double total, double current) {
                        int proX = (int) (current * 100 / total);
                        pBar.setProgress(proX >= 100 ? 99 : proX); // 这里就是关键的实时更新进度了！
                        pBar.setProgressNumberFormat(NumberFormatUtil.oneDecimal(total / 1024.00f / 1024.00f) + "M");
                    }
                });
            } else {
                ToastUtil.showShort("下载地址有误");
            }
        } else {
            ToastUtil.showShort("SD卡不可用，请插入SD卡");
        }
    }


    //安装应用的流程
    public static void installProcess(final Activity activity) {
        if (pBar != null && pBar.isShowing()) {
            pBar.cancel();
        }
        boolean haveInstallPermission;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            //先获取是否有安装未知来源应用的权限
            haveInstallPermission = activity.getPackageManager().canRequestPackageInstalls();

            if (!haveInstallPermission) {
                //没有权限
                DialogUtils.setPremission(activity, new BtnDoneListener() {
                    @Override
                    public void done(String content) {
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                            startInstallPermissionSettingActivity(activity);
                        }
                    }
                });
            } else {
                //有权限，开始安装应用程序
                down();
            }
        } else {
            //8.0一下的直接安装
            down();
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private static void startInstallPermissionSettingActivity(Activity activity) {
        //注意这个是8.0新API
        Intent intent = new Intent(Settings.ACTION_MANAGE_UNKNOWN_APP_SOURCES);
        activity.startActivityForResult(intent, 10086);
    }


    /**
     * 跳转至应用宝
     */

    public static void goToTencentMarket(Context context, String packageName) {
        Uri uri = Uri.parse("market://details?id=" + packageName);
        Intent goToMarket = new Intent(Intent.ACTION_VIEW, uri);
        try {
            goToMarket.setClassName("com.tencent.android.qqdownloader", "com.tencent.pangu.link.LinkProxyActivity");
            context.startActivity(goToMarket);
        } catch (ActivityNotFoundException e) {
            e.printStackTrace();
        }
    }
}

