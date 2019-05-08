package library.Retrofit_Http.icallBack;


import android.app.AlertDialog;
import android.content.Context;
import android.view.View;
import android.view.Window;
import android.widget.LinearLayout;


import com.dwz.mvvmdemo.R;

import java.lang.ref.WeakReference;

import library.Retrofit_Http.HttpTools.RxOkHttpLog;
import library.Retrofit_Http.RequBean.ResponseBean;


public abstract class ICallBack {


    // 请求数据进度条
    public AlertDialog dialogprogress = null;

    private WeakReference<Context> weakContext;

    private boolean isShowProgress;

    int i = 0;

//    private ICallBack() {
//
//    }

    /**
     *
     * @param context 上下文
     * @param isShowProgress 是否显示加载条
     */
    public ICallBack(Context context, boolean isShowProgress) {

        weakContext = new WeakReference<>(context);
        this.isShowProgress = isShowProgress;
    }

    public void onFinish() {
        // TODO Auto-generated method stub
        closeProgress();
    }

    public void onStart() {
        // TODO Auto-generated method stub
        // 显示进度条
        if (isShowProgress) {

            showProgress(weakContext.get());
        }
    }

    public abstract void onSuccess(ResponseBean responseBean);

    public abstract void onError(int code, String msg);

    public void showProgress(Context context) {
        // TODO Auto-generated method stub
        if (null != dialogprogress && !dialogprogress.isShowing()) {
            i++;
            RxOkHttpLog.d(i+"======");
            dialogprogress.show();
            return;
        }

        // 设置窗口的内容页面
        if ((null == dialogprogress || !dialogprogress.isShowing()) && context != null) {

            i++;
            RxOkHttpLog.d(i+"======");
            View view = LinearLayout.inflate(context, R.layout.progress,null);
            dialogprogress = new AlertDialog.Builder(context)
                    .setView(view)
                    .create();
            dialogprogress.show();
            dialogprogress.setCancelable(true);

            Window window = dialogprogress.getWindow();
            window.setBackgroundDrawableResource(android.R.color.transparent);

            // *** 主要就是在这里实现这种效果的.
            // 设置窗口的内容页面,shrew_exit_dialog.xml文件中定义view内容
            window.setContentView(R.layout.progress);
        }
    }

    /***
     * 关闭进度框
     */
    public void closeProgress() {

        if (null != dialogprogress && dialogprogress.isShowing()) {
            // 关闭对话框
            dialogprogress.dismiss();
        }

        dialogprogress = null;
    }
}
