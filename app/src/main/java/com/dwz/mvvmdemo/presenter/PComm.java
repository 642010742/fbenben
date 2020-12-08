package com.dwz.mvvmdemo.presenter;

import android.content.Context;

import io.reactivex.disposables.CompositeDisposable;
import library.Retrofit_Http.RequBean.RequestBean;
import library.Retrofit_Http.RequBean.ResponseBean;
import library.Retrofit_Http.RxRetrofitClient;
import library.Retrofit_Http.icallBack.ICallBack;
import library.interfaces.IPBaseCallBack;
import library.presenter.BasePresenter;

public class PComm extends BasePresenter {
    private IPBaseCallBack ipBaseCallBack;
    private CompositeDisposable compositeDisposable;
    public PComm(IPBaseCallBack ipBaseCallBack, CompositeDisposable compositeDisposable) {
        this.ipBaseCallBack = ipBaseCallBack;
        this.compositeDisposable = compositeDisposable;
    }

    /**
     * 修改密码
     * @param context
     * @param requestBean
     * @param type
     * @param isShowProgress
     * 针对结果回调出去,在该类没有特殊处理的请求
     */
    public void commenMethod(Context context, RequestBean requestBean, final int type, boolean isShowProgress) {

        RxRetrofitClient.getClient().execute(requestBean, null, new ICallBack(context, isShowProgress) {
            @Override
            public void onSuccess(ResponseBean responseBean) {
                ipBaseCallBack.onSuccess(type, responseBean.getAccess_token());
            }

            @Override
            public void onError(int code, String msg) {
                ipBaseCallBack.onError(type, code, msg);
            }
        },compositeDisposable);
    }
}
