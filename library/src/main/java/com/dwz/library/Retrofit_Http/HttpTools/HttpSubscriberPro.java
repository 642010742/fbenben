package com.dwz.library.Retrofit_Http.HttpTools;

import com.dwz.library.Retrofit_Http.icallBack.ICallBack;
import com.dwz.library.Retrofit_Http.icallBack.ICallBackPro;
import com.dwz.library.utils.ToastUtil;

import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;


/**
 * Created by Administrator on 2017/3/16.
 */

public class HttpSubscriberPro implements Subscriber {

    private ICallBack iCallBack;
    private ICallBackPro iCallBackPro;
    private Class clazz;

    private long startTime;

    public HttpSubscriberPro(ICallBack iCallBack, Class clazz) {
        this.iCallBack = iCallBack;
        this.clazz = clazz;
    }

    public HttpSubscriberPro(ICallBackPro iCallBack, Class clazz) {
        this.iCallBackPro = iCallBack;
        this.clazz = clazz;
    }

    @Override
    public void onComplete() {
        if (iCallBack == null) {
            iCallBackPro.onFinish();
        } else {

            iCallBack.onFinish();
        }
    }

    @Override
    public void onError(Throwable e) {
        e.printStackTrace();
        long endTime = System.currentTimeMillis();
        //数据请求成功，只是个成功标示
        RxOkHttpLog.d("请求失败---------->" + e.toString());
        if ((endTime - startTime) / 1000 > 5) {
            startTime = System.currentTimeMillis();

            ToastUtil.showShort("服务器超时");
        }
        onComplete();
    }

    @Override
    public void onSubscribe(Subscription s) {

    }

    @Override
    public void onNext(Object o) {

    }

//    @Override
//    public void onNext(Response response) {
//
//    }

//    @Override
//    public void onNext(ResponseBean responseBean) {
//        ResponseBean respBean = HttpDataTools.getResponseData(responseBean, clazz);
//        switch (respBean.getStatus()) {
//            case 200:
//                if (iCallBack == null) {
//                    iCallBackPro.onSuccess(responseBean);
//                } else {
//
//                    iCallBack.onSuccess(responseBean);
//                }
//                break;
//            default:
//                if (iCallBack == null) {
//                    iCallBackPro.onError(respBean.getStatus(), respBean.getMessage());
//                } else {
//
//                    iCallBack.onError(respBean.getStatus(), respBean.getMessage());
//                }
//                break;
//        }
//    }
}
