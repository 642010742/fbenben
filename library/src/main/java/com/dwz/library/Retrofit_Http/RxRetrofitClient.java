package com.dwz.library.Retrofit_Http;

import com.dwz.library.App.HttpConstants;
import com.dwz.library.Retrofit_Http.HttpTools.BaseObserver;
import com.dwz.library.Retrofit_Http.HttpTools.HttpDataTools;
import com.dwz.library.Retrofit_Http.RequBean.RequestBean;
import com.dwz.library.Retrofit_Http.apiservies.ApiServer;
import com.dwz.library.Retrofit_Http.icallBack.ICallBack;
import com.dwz.library.Retrofit_Http.inerceptor.BaseUrlInterceptor;
import com.dwz.library.Retrofit_Http.inerceptor.HeaderInterceptor;
import com.dwz.library.listener.UploadProgressListener;
import com.dwz.library.utils.LogUtils;

import java.util.concurrent.TimeUnit;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;


/**
 *
 */
public class RxRetrofitClient {

    private static RxRetrofitClient mClient;
    private OkHttpClient mOkHttpClient;
    private ApiServer apiServer;
    private Retrofit retrofit;

    private final long READ_TIMEOUT = 20;
    private final long WRITE_TIMEOUT = 30;
    private final long CONNECT_TIMEOUT = 10;

    private RxRetrofitClient() {
        if (mOkHttpClient == null) {
            mOkHttpClient = new OkHttpClient.Builder()
                    .addInterceptor(new HeaderInterceptor())
                    .addInterceptor(new BaseUrlInterceptor())
                    .readTimeout(READ_TIMEOUT, TimeUnit.SECONDS)//设置读取超时时间
                    .writeTimeout(WRITE_TIMEOUT, TimeUnit.SECONDS)//设置写的超时时间
                    .connectTimeout(CONNECT_TIMEOUT, TimeUnit.SECONDS)//设置连接超时时间
                    .build();
        }
        retrofit = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .baseUrl(HttpConstants.BASE_DEV_URL)
                .client(mOkHttpClient)
                .build();

        apiServer = retrofit.create(ApiServer.class);
    }


    public static RxRetrofitClient getClient() {
        if (mClient == null) {
            synchronized (RxRetrofitClient.class) {
                mClient = new RxRetrofitClient();
            }
        }
        return mClient;
    }

    //请求后台数据
    public void execute(final RequestBean requestBean, final Class clazz, final ICallBack iCallBack,
                        CompositeDisposable compositeDisposable) {
        iCallBack.onStart();
        if (HttpConstants.METHOD_GET.equalsIgnoreCase(requestBean.getRequestMethod())) {
            if (requestBean.isPostQuery()) {
                apiServer.get(HttpDataTools.getPath(requestBean.getPath()))
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new BaseObserver(iCallBack, clazz,compositeDisposable));
            } else {
                apiServer.get(HttpDataTools.getPath(requestBean.getPath()), HttpDataTools.getGetSendData(requestBean.getBsrqBean()))
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new BaseObserver(iCallBack, clazz,compositeDisposable));
            }
        } else if (HttpConstants.METHOD_POST.equalsIgnoreCase(requestBean.getRequestMethod())) {
//            实体类提交
            if (requestBean.isPostQuery()) {
                apiServer.postQuery(HttpDataTools.getPath(requestBean.getPath()), HttpDataTools.getGetSendData(requestBean.getBsrqBean()))
                        //form表单提交
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new BaseObserver(iCallBack, clazz,compositeDisposable));
            } else {
                if (requestBean.isDownLoad()) {
                    LogUtils.loge("============上传进度==1111=");
                    apiServer.upLoadFile(HttpDataTools.getPath(requestBean.getPath()), HttpDataTools.getUploadPart(requestBean, new UploadProgressListener() {
                        @Override
                        public void onProgress(long currentBytesCount, long totalBytesCount) {
                            LogUtils.loge("============上传进度===" + currentBytesCount + "===" + totalBytesCount);
                        }
                    }))
                            //form表单提交
                            .subscribeOn(Schedulers.io())
                            .observeOn(AndroidSchedulers.mainThread())
                            .subscribe(new BaseObserver(iCallBack, clazz,compositeDisposable));
                } else {
                    apiServer.post(HttpDataTools.getPath(requestBean.getPath()), HttpDataTools.getPostSendData(requestBean))
                            //form表单提交
                            .subscribeOn(Schedulers.io())
                            .observeOn(AndroidSchedulers.mainThread())
                            .subscribe(new BaseObserver(iCallBack, clazz,compositeDisposable));
                }
            }
        } else if (HttpConstants.METHOD_PUT.equalsIgnoreCase(requestBean.getRequestMethod())) {
//            实体类提交
            if (requestBean.isPostQuery()) {
                apiServer.put(HttpDataTools.getPath(requestBean.getPath()), HttpDataTools.getGetSendData(requestBean.getBsrqBean()))
                        //form表单提交
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new BaseObserver(iCallBack, clazz,compositeDisposable));
            } else {
                apiServer.put(HttpDataTools.getPath(requestBean.getPath()), HttpDataTools.getPostSendData(requestBean))
                        //form表单提交
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new BaseObserver(iCallBack, clazz,compositeDisposable));
            }
        } else if (HttpConstants.METHOD_DELETE.equalsIgnoreCase(requestBean.getRequestMethod())) {
//            实体类提交
            if (requestBean.isPostQuery()) {
                apiServer.delete(HttpDataTools.getPath(requestBean.getPath()), HttpDataTools.getGetSendData(requestBean.getBsrqBean()))
                        //form表单提交
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new BaseObserver(iCallBack, clazz,compositeDisposable));
            } else {
                apiServer.delete(HttpDataTools.getPath(requestBean.getPath()))
                        //form表单提交
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new BaseObserver(iCallBack, clazz,compositeDisposable));
            }
        }
    }


//    //上传请求
//    public Subscription execute(final RequestBean requestBean, final Class clazz, ICallBack iCallBack, final ProgressRequestListener listener) {
//
//        //上传
//        LogUtils.d("执行上传！！！！！！！！！");
//        return apiServer.upLoad(HttpDataTools.getPath(requestBean.getPath()), HttpDataTools.getUploadSendData(requestBean, listener))
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(new_w BaseObserver(iCallBack, clazz));
//
//    }
//
//    //下载请求
//    public Subscription executeDown(final RequestBean requestBean, final Class clazz, ICallBack iCallBack) {
//
//        //下载
//        LogUtils.d("执行下载！！！！！！！！！");
//        return apiDownServer.downLoad(HttpDataTools.getPath(requestBean.getPath()))
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(new_w BaseObserver(iCallBack, clazz));
//
//    }

}