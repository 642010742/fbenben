package com.dwz.library.Retrofit_Http.HttpTools;

import com.dwz.library.App.AppManager;
import com.dwz.library.Retrofit_Http.RequBean.ResponseBean;
import com.dwz.library.Retrofit_Http.icallBack.ICallBack;
import com.dwz.library.Retrofit_Http.icallBack.ICallBackPro;

import org.json.JSONException;
import org.json.JSONObject;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;
import java.io.IOException;
import okhttp3.ResponseBody;
import retrofit2.HttpException;
import retrofit2.Response;


/**
 * Created by Administrator on 2017/3/16.
 */

public class HttpSubscriber implements Subscriber<ResponseBean> {

    private String identifyString = "访问此资源需要完全的身份验证";
    private ICallBack iCallBack;
    private ICallBackPro iCallBackPro;
    private Class clazz;

    private long startTime;

    public HttpSubscriber(ICallBack iCallBack, Class clazz) {
        this.iCallBack = iCallBack;
        this.clazz = clazz;
    }

    public HttpSubscriber(ICallBackPro iCallBack, Class clazz) {
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
        if (e.toString().contains("com.google.gson.JsonSyntaxException")) {
            //       ToastUtil.showShort("数据解析异常");
            return;
        }

        /*******************************自己加的****************************************/
        if (e instanceof HttpException) {
            HttpException exception = (HttpException) e;
            Response<?> response = exception.response();
            ResponseBody body = response.errorBody();
            try {
                String out = new String(body.bytes());
                try {
                    JSONObject jsonObject = new JSONObject(out);
                    String error_description1 = jsonObject.optString("error_description");
                    int code = jsonObject.optInt("code");
                    String msg = jsonObject.optString("msg");
                    if (code == 0 && msg.equals(identifyString)) {
                        //表明token失效,需要登录
                        //ToastUtil.showShort("您的验证已过期,请重新登录");
                        //toLoginActivity();
                    } else {
                        //只有登录接口是这样的
                        if (iCallBack == null) {
                            iCallBackPro.onError(400, error_description1);
                        } else {
                            iCallBack.onError(400, error_description1);
                        }
                    }
                } catch (JSONException e1) {
                    e1.printStackTrace();
                }
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }
/*******************************自己加的****************************************/
        RxOkHttpLog.d("请求失败---------->" + e.toString());
        if ((endTime - startTime) / 1000 > 5) {
            startTime = System.currentTimeMillis();
//            ToastUtil.showShort("服务器超时");
        }
        onComplete();
    }


    @Override
    public void onSubscribe(Subscription s) {

    }

    @Override
    public void onNext(ResponseBean responseBean) {
        ResponseBean respBean = HttpDataTools.getResponseData(responseBean, clazz);
        switch (respBean.getCode()) {
            case 1:
                if (iCallBack == null) {
                    iCallBackPro.onSuccess(responseBean);
                } else {
                    iCallBack.onSuccess(responseBean);
                }
                break;


//            case 401://Token失效跳转登录清除Token
//                if(!AppManager.getAppManager().currentActivity().getClass().getSimpleName().contains("LoginActivity")){
//                    SpManager.clearLoginInfo();
//                    toLoginActivity();
//                }else{
//                    ToastUtil.showLong(respBean.getMsg());
//                }
//                break;
            default:
                if (iCallBack == null) {
                    iCallBackPro.onError(respBean.getCode(), respBean.getMsg());
                } else {
                    iCallBack.onError(respBean.getCode(), respBean.getMsg());
                }
                break;
        }
    }

    public void toLoginActivity() {
//        SpManager.clearLoginInfo();
        if (AppManager.getAppManager().currentActivity() != null) {
//            AppManager.getAppManager().currentActivity().startActivity(new Intent(AppManager.getAppManager().currentActivity(), LoginActivity.class));
//            Intent intent = new Intent(AppContext.getmInstance(), LoginActivity.class);
//            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//            AppContext.getmInstance().startActivity(intent);
            AppManager.getAppManager().finishAllActivity();
        } else {
//            Intent intent = new Intent(AppContext.getmInstance(), LoginActivity.class);
//            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//            AppContext.getmInstance().startActivity(intent);
        }
    }
}
