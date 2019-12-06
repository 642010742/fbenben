package library.utils.upapk;

/**
 * Created by Administrator on 2018/4/16.
 */

import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import library.Retrofit_Http.inerceptor.HeaderInterceptor;
import library.utils.LogUtils;
import library.utils.ToastUtil;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;


public class DownloadManager {


    public DownloadManager() {
        mDelivery = new Handler(Looper.getMainLooper());
    }

    private Handler mDelivery;     //主线程返回

    private Call downCall;         //下载的call

    private static OkHttpClient mOkHttpClient;

    private static DownloadManager mInstance;    //单例

    public static DownloadManager getInstance() {
        if (mInstance == null) {
            synchronized (DownloadManager.class) {
                if (mInstance == null) {
                    mInstance = new DownloadManager();
                }
            }
        }
        return mInstance;
    }

    public boolean isCanceled() {

        return downCall != null ? downCall.isCanceled() : false;
    }

    //synchronized修饰的静态方法锁定的是这个类的所有对象,保证在同一时刻最多只有一个线程执行该段代码
    public synchronized OkHttpClient getOkHttpClient() {
        if (null == mOkHttpClient) {
            try {
                mOkHttpClient = newOkHttpClient();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return mOkHttpClient;
    }

    /**
     * 创建okhttp
     *
     * @return
     * @throws Exception
     */
    private OkHttpClient newOkHttpClient() throws Exception {

        //创建okHttpClient对象
        OkHttpClient.Builder okHttpBuild = new OkHttpClient.Builder()
                .addInterceptor(new HeaderInterceptor());
        mOkHttpClient = okHttpBuild.build();
//        TrustManager tm = new OkHttpManager.myTrustManager();
//        SSLContext sslContext = SSLContext.getInstance("TLS");
//        sslContext.init(null, new TrustManager[]{tm}, null);
//
//        mOkHttpClient.setSslSocketFactory(sslContext.getSocketFactory());


        return mOkHttpClient;
    }


    /**
     * 异步下载文件
     *
     * @param url         文件的下载地址
     * @param destFileDir 本地文件存储的文件夹
     * @param callback
     */
    private void okHttpDownload(final String url, final String destFileDir, final String fileName, final ResultCallback callback) {

        if (TextUtils.isEmpty(url)) {
            ToastUtil.showShort("下载失败，请先检查您的网络或者读写权限");
            return;
        }
        final Request request = new Request.Builder()
                .url(url)
                .build();

        downCall = getOkHttpClient().newCall(request);
        downCall.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

                sendFailedStringCallback(request, e, callback);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {

                InputStream is = null;
                byte[] buf = new byte[2048];
                int len = 0;
                FileOutputStream fos = null;
                try {
                    double current = 0;
                    double total = response.body().contentLength();
                    if (total < 1024) {
                        total = 20 * 1024 * 1024;
                    }
                    is = response.body().byteStream();
                    File dir = new File(destFileDir);
                    File file = new File(dir, fileName);
                    if (!dir.exists()) {
                        dir.mkdirs();
                    }
                    if (!file.exists()) {
                        file.createNewFile();
                    }
                    fos = new FileOutputStream(file);
                    while ((len = is.read(buf)) != -1) {
                        current += len;
                        fos.write(buf, 0, len);
                        LogUtils.logd("download current------>" + current);
                        LogUtils.logd("download total------>" + total);
                        sendProgressCallBack(total, current, callback);
                    }
                    fos.flush();
                    //如果下载文件成功，第一个参数为文件的绝对路径
                    sendSuccessResultCallback(file.getAbsolutePath(), callback);
                } catch (IOException e) {
                    sendFailedStringCallback(response.request(), e, callback);
                } finally {
                    try {
                        if (is != null) {
                            is.close();
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    try {
                        if (fos != null) {
                            fos.close();
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }

            }
        });
    }


    /**
     * 异步下载文件
     *
     * @param url         文件的下载地址
     * @param destFileDir 本地文件存储的文件夹
     * @param callback
     */
    private void okHttpDownload(final String url, final String destFileDir, final String fileName, final ResultCallback callback, final double defultTotal) {

        final Request request = new Request.Builder()
                .addHeader("Accept-Encoding", "identity")
                .url(url)
                .build();

        downCall = getOkHttpClient().newCall(request);
        downCall.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

                sendFailedStringCallback(request, e, callback);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {

                InputStream is = null;
                byte[] buf = new byte[2048];
                int len = 0;
                FileOutputStream fos = null;
                try {
                    double current = 0;
                    is = response.body().byteStream();
                    File dir = new File(destFileDir);
                    File file = new File(destFileDir, fileName);
                    if (!dir.exists()) {
                        dir.mkdirs();
                    }
                    if (!file.exists()) {
                        file.createNewFile();
                    }
                    fos = new FileOutputStream(file);
                    double total = response.body().contentLength();
                    if (total < 1024) {
                        total = defultTotal;
                    }
                    while ((len = is.read(buf)) != -1) {
                        current += len;
                        fos.write(buf, 0, len);
                        LogUtils.logd("download current------>" + current);
                        LogUtils.logd("download total------>" + total);
                        sendProgressCallBack(total, current, callback);
                    }
                    fos.flush();
                    //如果下载文件成功，第一个参数为文件的绝对路径
                    sendSuccessResultCallback(file.getAbsolutePath(), callback);
                } catch (IOException e) {
                    sendFailedStringCallback(response.request(), e, callback);
                } finally {
                    try {
                        if (is != null) {
                            is.close();
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    try {
                        if (fos != null) {
                            fos.close();
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }

            }
        });
    }

    //下载失败ui线程回调
    private void sendFailedStringCallback(final Request request, final Exception e, final ResultCallback callback) {
        mDelivery.post(new Runnable() {
            @Override
            public void run() {
                if (callback != null) {

                    callback.onError(request, e);
                }
            }
        });
    }

    //下载成功ui线程回调
    private void sendSuccessResultCallback(final Object object, final ResultCallback callback) {
        mDelivery.post(new Runnable() {
            @Override
            public void run() {
                if (callback != null) {
                    callback.onResponse(object);
                }
            }
        });
    }


    //下载回调接口
    public static abstract class ResultCallback<T> {

        //下载错误
        public abstract void onError(Request request, Exception e);

        //下载成功
        public abstract void onResponse(T response);

        //下载进度
        public abstract void onProgress(double total, double current);
    }


    /**
     * 进度信息ui线程回调
     *
     * @param total    总计大小
     * @param current  当前进度
     * @param callBack
     * @param <T>
     */
    private <T> void sendProgressCallBack(final double total, final double current, final ResultCallback<T> callBack) {
        mDelivery.post(new Runnable() {
            @Override
            public void run() {
                if (callBack != null) {
                    callBack.onProgress(total, current);
                }
            }
        });
    }


    /*  下面为对外开放接口 */


    /**
     * 下载文件
     *
     * @param url      文件链接
     * @param destDir  下载保存地址
     * @param callback 回调
     */
    public static void downloadFile(String url, String destDir, String fileName, ResultCallback callback) {
        getInstance().okHttpDownload(url, destDir, fileName, callback);
    }

    /**
     * 下载文件
     *
     * @param url      文件链接
     * @param destDir  下载保存地址
     * @param callback 回调
     */
    public static void downloadFile(String url, String destDir, String fileName, ResultCallback callback, double deftotal) {
        getInstance().okHttpDownload(url, destDir, fileName, callback, deftotal);
    }


    /**
     * 取消下载
     */
    public static void cancleDown() {
        if (getInstance() != null && getInstance().downCall != null) {
            getInstance().downCall.cancel();
        }
    }
}
